package com.example.authur.common.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 * @Author: jibing.Li
 * @Date: 2021/10/28 12:43
 */
@Data
public class AuthurAuthUser implements Serializable {
    private static final long serialVersionUID = -1748289340320186418L;

    private String username;

    private String password;

    private boolean accountNonExpired = true;

    private boolean accountNonLocked= true;

    private boolean credentialsNonExpired= true;

    private boolean enabled= true;
}
