package com.example.authur.gateway.filter;

import com.example.authur.common.entity.AuthurResponse;
import com.example.authur.common.utils.AuthurUtils;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.post.SendErrorFilter;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import javax.servlet.http.HttpServletResponse;

/**
 * @Description:
 * @Author: jibing.Li
 * @Date: 2021/11/1 16:09
 */
@Slf4j
@Component
public class AuthurGatewayErrorFilter extends SendErrorFilter {
    @Override
    public Object run() {
        try {
            AuthurResponse authurResponse = new AuthurResponse();
            RequestContext ctx = RequestContext.getCurrentContext();
            String serviceId = (String) ctx.get(FilterConstants.SERVICE_ID_KEY);

            ExceptionHolder exception = findZuulException(ctx.getThrowable());
            String errorCause = exception.getErrorCause();
            Throwable throwable = exception.getThrowable();
            String message = throwable.getMessage();
            message = StringUtils.isBlank(message) ? errorCause : message;
            authurResponse = resolveExceptionMessage(message, serviceId, authurResponse);

            HttpServletResponse response = ctx.getResponse();
            AuthurUtils.makeResponse(
                    response, MediaType.APPLICATION_JSON_UTF8_VALUE,
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR, authurResponse
            );
            log.error("Zull sendError：{}", authurResponse.getMessage());
        } catch (Exception ex) {
            log.error("Zuul sendError", ex);
            ReflectionUtils.rethrowRuntimeException(ex);
        }
        return null;
    }

    private AuthurResponse resolveExceptionMessage(String message, String serviceId, AuthurResponse authurResponse) {
        if (StringUtils.containsIgnoreCase(message, "time out")) {
            return authurResponse.message("请求" + serviceId + "服务超时");
        }
        if (StringUtils.containsIgnoreCase(message, "forwarding error")) {
            return authurResponse.message(serviceId + "服务不可用");
        }
        return authurResponse.message("Zuul请求" + serviceId + "服务异常");
    }
}
