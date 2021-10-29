package com.example.authur.auth.controller;

import com.example.authur.common.AuthurAuthException;
import com.example.authur.common.entity.AuthurResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * @Description:
 * @Author: jibing.Li
 * @Date: 2021/10/28 12:39
 */
@RestController
public class SecurityController {
    @Autowired
    ConsumerTokenServices consumerTokenServices;

    @GetMapping("oauth/test")
    public String testOauth() {
        return "oauth";
    }

    @GetMapping("user")
    public Principal currentUser(Principal principal) {
        return principal;
    }

    @DeleteMapping("signout")
    public AuthurResponse signout(HttpServletRequest request) throws AuthurAuthException {
        String authorization = request.getHeader("Authorization");
        String token = StringUtils.replace(authorization, "bearer ", "");
        AuthurResponse authurResponse = new AuthurResponse();
        if (!consumerTokenServices.revokeToken(token)) {
            throw new AuthurAuthException("退出登录失败");
        }
        return authurResponse.message("退出登录成功");
    }
}
