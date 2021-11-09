package com.example.authur.common.configure;

import com.example.authur.common.entity.AuthurConstant;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.util.Base64Utils;

/**
 * @Description:
 * @Author: jibing.Li
 * @Date: 2021/11/9 11:53
 */
public class AuthurOAuth2FeignConfigure {

    @Bean
    public RequestInterceptor oauth2FeignRequsetInterceptor(){
        return requestTemplate -> {
            //添加 Zuul Token
            String zuulToken = new String(Base64Utils.encode(AuthurConstant.ZUUL_TOKEN_VALUE.getBytes()));
            requestTemplate.header(AuthurConstant.ZUUL_TOKEN_HEADER, zuulToken);

            Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
            if (details instanceof OAuth2AuthenticationDetails){
                String tokenValue = ((OAuth2AuthenticationDetails) details).getTokenValue();
                requestTemplate.header(HttpHeaders.AUTHORIZATION, "bearer "+tokenValue);
            }
        };
    }
}
