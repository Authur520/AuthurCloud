package com.example.authur.common.entity;

import java.util.HashMap;

/**
 * @Description:
 * @Author: jibing.Li
 * @Date: 2021/10/28 12:37
 */
public class AuthurResponse extends HashMap<String, Object> {
    private static final long serialVersionUID = -8713837118340960775L;

    public AuthurResponse message(String message) {
        this.put("message", message);
        return this;
    }

    public AuthurResponse data(Object data) {
        this.put("data", data);
        return this;
    }

    @Override
    public AuthurResponse put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public String getMessage() {
        return String.valueOf(get("message"));
    }

    public Object getData() {
        return get("data");
    }

}
