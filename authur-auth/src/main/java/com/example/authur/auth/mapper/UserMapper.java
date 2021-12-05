package com.example.authur.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.authur.common.entity.SystemUser;

/**
 * @Description:
 * @Author: jibing.Li
 * @Date: 2021/12/2 10:22
 */
public interface UserMapper extends BaseMapper<SystemUser> {
    SystemUser findByName(String username);
}
