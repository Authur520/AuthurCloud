package com.example.authur.server.system.properties;

import lombok.Data;

/**
 * @Description:
 * @Author: jibing.Li
 * @Date: 2022/1/19 11:09
 */
@Data
public class AuthurSwaggerProperties {

    private String basePackage;
    private String title;
    private String description;
    private String version;
    private String author;
    private String url;
    private String email;
    private String license;
    private String licenseUrl;

    private String grantUrl;
    private String name;
    private String scope;
}
