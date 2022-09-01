package com.example.authur.common.handler;

import com.example.authur.common.entity.AuthurResponse;
import com.example.authur.common.utils.AuthurUtils;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: 403用户无权限
 * @Author: jibing.Li
 * @Date: 2021/11/1 15:28
 */
public class AuthurAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        AuthurResponse response = new AuthurResponse();
        AuthurUtils.makeResponse(httpServletResponse,
                MediaType.APPLICATION_JSON_VALUE,
                HttpServletResponse.SC_FORBIDDEN,
                response.message("没有权限访问该资源"));
    }
}
