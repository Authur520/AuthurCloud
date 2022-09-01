package com.example.authur.common.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.example.authur.common.entity.AuthurConstant;
import com.example.authur.common.entity.AuthurResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.util.Base64Utils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description:
 * @Author: jibing.Li
 * @Date: 2021/11/9 16:26
 */
public class AuthurServerProtectInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        // 从请求头中获取 Zuul Token
        String token = request.getHeader(AuthurConstant.ZUUL_TOKEN_HEADER);
        String zuulToken = new String(Base64Utils.encode((AuthurConstant.ZUUL_TOKEN_VALUE).getBytes()));
        // 验证 Zuul Token 的正确性
        if (StringUtils.equals(zuulToken, token)){
            return true;
        }else {
            AuthurResponse authurResponse = new AuthurResponse();
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write(JSONObject.toJSONString(authurResponse.message("请通过网关获取资源")));
            return false;
        }
    }
}
