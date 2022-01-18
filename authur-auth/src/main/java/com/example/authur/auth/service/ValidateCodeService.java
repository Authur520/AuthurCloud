package com.example.authur.auth.service;

import com.example.authur.auth.configure.AuthurAuthProperties;
import com.example.authur.auth.properties.AuthurValidateCodeProperties;
import com.example.authur.common.entity.AuthurConstant;
import com.example.authur.common.exception.ValidateCodeException;
import com.example.authur.common.service.RedisService;
import com.wf.captcha.GifCaptcha;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description:
 * @Author: jibing.Li
 * @Date: 2022/1/11 14:26
 */
@Service
public class ValidateCodeService {

    @Autowired
    private RedisService redisService;

    @Autowired
    private AuthurAuthProperties properties;

    /**
     * 生成验证码
     * @param request
     * @param response
     * @throws IOException
     * @throws ValidateCodeException
     */
    public void create(HttpServletRequest request, HttpServletResponse response) throws IOException, ValidateCodeException {
        String key = request.getParameter("key");
        if (StringUtils.isBlank(key)){
            throw new ValidateCodeException("验证码key不能为空");
        }
        AuthurValidateCodeProperties code = properties.getCode();
        setHandler(response,code.getType());

        Captcha captcha = createCaptcha(code);
        redisService.set(AuthurConstant.CODE_PREFIX + key, StringUtils.lowerCase(captcha.text()), code.getTime());
        captcha.out(response.getOutputStream());
    }

    /**
     * 效验验证码
     * @param key    前端上送key
     * @param value  前端上送待校验值
     * @throws ValidateCodeException
     */
    public void check(String key, String value) throws ValidateCodeException {
        Object codeInRedis = redisService.get(AuthurConstant.CODE_PREFIX + key);

        if (StringUtils.isBlank(value)){
            throw new ValidateCodeException("请输入验证码");
        }
        if (codeInRedis == null){
            throw new ValidateCodeException("验证码已过期");
        }
        if (!StringUtils.equalsIgnoreCase(value,String.valueOf(codeInRedis))){
            throw new ValidateCodeException("验证码不正确");
        }

    }

    private Captcha createCaptcha(AuthurValidateCodeProperties code) {
        Captcha captcha = null;
        if (StringUtils.equalsIgnoreCase(code.getType(), AuthurConstant.GIF)){
            captcha = new GifCaptcha(code.getWidth(), code.getHeight(), code.getLength());
        }else {
            captcha = new SpecCaptcha(code.getWidth(), code.getHeight(), code.getLength());
        }
        captcha.setCharType(code.getCharType());
        return captcha;
    }

    private void setHandler(HttpServletResponse response, String type) {
        if (StringUtils.equalsIgnoreCase(type, AuthurConstant.GIF)){
            response.setContentType(MediaType.IMAGE_GIF_VALUE);
        }else {
            response.setContentType(MediaType.IMAGE_PNG_VALUE);
        }
        response.setHeader(HttpHeaders.PRAGMA, "No-cache");
        response.setHeader(HttpHeaders.CACHE_CONTROL, "no-cache");
        response.setDateHeader(HttpHeaders.EXPIRES, 0L);
    }


}
