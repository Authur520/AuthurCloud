package com.example.authur.server.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.authur.common.entity.UserRole;

/**
 * @Description:
 * @Author: jibing.Li
 * @Date: 2022/1/18 15:47
 */
public interface IUserRoleService extends IService<UserRole> {

    void deleteUserRolesByRoleId(String[] roleIds);

    void deleteUserRolesByUserId(String[] userIds);
}
