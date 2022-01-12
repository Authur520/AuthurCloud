package com.example.authur.common.exception;

/**
 * @Description:
 * @Author: jibing.Li
 * @Date: 2022/1/11 14:24
 */
public class ValidateCodeException extends Exception {

    private static final long serialVersionUID = 7514854456967620043L;

    public ValidateCodeException(String message){
        super(message);
    }
}
