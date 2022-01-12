package com.example.authur.common.entity;

/**
 * @Description:
 * @Author: jibing.Li
 * @Date: 2021/11/9 16:22
 */
public class AuthurConstant {
    /**
     * Zuul请求头TOKEN名称（不要有空格）
     */
    public static final String ZUUL_TOKEN_HEADER = "ZuulToken";
    /**
     * Zuul请求头TOKEN值
     */
    public static final String ZUUL_TOKEN_VALUE = "authur:zuul:123456";

    /**
     * gif类型
     */
    public static final String GIF = "gif";
    /**
     * png类型
     */
    public static final String PNG = "png";

    /**
     * 验证码 key前缀
     */
    public static final String CODE_PREFIX = "febs.captcha.";
}
