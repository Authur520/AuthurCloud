package com.example.authur.common.handler;


import com.example.authur.common.entity.AuthurResponse;
import com.example.authur.common.utils.AuthurUtils;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: 令牌不正确返回401
 * @Author: jibing.Li
 * @Date: 2021/11/1 15:14
 */
public class AuthurAuthExceptionEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        AuthurResponse authurResponse = new AuthurResponse();
        AuthurUtils.makeResponse(response,
                MediaType.APPLICATION_JSON_VALUE,
                HttpServletResponse.SC_UNAUTHORIZED,
                authurResponse.message("token无效"));
    }
}
