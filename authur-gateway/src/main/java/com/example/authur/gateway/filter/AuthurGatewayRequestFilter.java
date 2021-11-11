package com.example.authur.gateway.filter;

import com.example.authur.common.entity.AuthurConstant;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description:
 * @Author: jibing.Li
 * @Date: 2021/11/9 16:11
 */
@Slf4j
@Component
public class AuthurGatewayRequestFilter extends ZuulFilter {
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
        byte[] token = Base64Utils.encode((AuthurConstant.ZUUL_TOKEN_VALUE).getBytes());
        requestContext.addZuulRequestHeader(AuthurConstant.ZUUL_TOKEN_HEADER, new String(token));
        return null;
    }
}
