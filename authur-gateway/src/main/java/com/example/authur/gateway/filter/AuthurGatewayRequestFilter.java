package com.example.authur.gateway.filter;

import com.example.authur.common.entity.AuthurConstant;
import com.example.authur.common.entity.AuthurResponse;
import com.example.authur.common.utils.AuthurUtils;
import com.example.authur.gateway.properties.AuthurGatewayProperties;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.Base64Utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description:
 * @Author: jibing.Li
 * @Date: 2021/11/9 16:11
 */
@Slf4j
@Component
public class AuthurGatewayRequestFilter extends ZuulFilter {

    @Autowired
    private AuthurGatewayProperties properties;

    private AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 6;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run(){
        RequestContext requestContext = RequestContext.getCurrentContext();
        String serviceId = (String) requestContext.get(FilterConstants.SERVICE_ID_KEY);
        HttpServletRequest request = requestContext.getRequest();
        String host = request.getRemoteHost();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        log.info("请求URI：{}，HTTP Method：{}，请求IP：{}，ServerId：{}", uri, method, host, serviceId);

        // 禁止外部访问资源实现
        boolean shouldForward = true;
        String forbidRequestUri = properties.getForbidRequestUri();
        String[] forbidRequestUris = StringUtils.splitByWholeSeparatorPreserveAllTokens(forbidRequestUri, ",");
        if (forbidRequestUris != null && ArrayUtils.isNotEmpty(forbidRequestUris)) {
            for (String u : forbidRequestUris) {
                if (pathMatcher.match(u, uri)) {
                    shouldForward = false;
                }
            }
        }
        if (!shouldForward) {
            HttpServletResponse response = requestContext.getResponse();
            AuthurResponse febsResponse = new AuthurResponse().message("该URI不允许外部访问");
            try {

                AuthurUtils.makeResponse(
                        response, MediaType.APPLICATION_JSON_UTF8_VALUE,
                        HttpServletResponse.SC_FORBIDDEN, febsResponse
                );
                requestContext.setSendZuulResponse(false);
                requestContext.setResponse(response);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }


        byte[] token = Base64Utils.encode((AuthurConstant.ZUUL_TOKEN_VALUE).getBytes());
        requestContext.addZuulRequestHeader(AuthurConstant.ZUUL_TOKEN_HEADER, new String(token));
        return null;
    }

    private class AuthurUtil {
    }
}
