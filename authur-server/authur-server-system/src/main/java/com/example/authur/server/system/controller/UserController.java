package com.example.authur.server.system.controller;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.example.authur.common.entity.AuthurResponse;
import com.example.authur.common.entity.QueryRequest;
import com.example.authur.common.entity.SystemUser;
import com.example.authur.common.exception.AuthurException;
import com.example.authur.common.utils.AuthurUtils;
import com.example.authur.server.system.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Map;

/**
 * @Description:
 * @Author: jibing.Li
 * @Date: 2022/1/18 15:54
 */

@Slf4j
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('user:view')")
    public AuthurResponse userList(QueryRequest queryRequest, SystemUser user) {
        Map<String, Object> dataTable = AuthurUtils.getDataTable(userService.findUserDetail(user, queryRequest));
        return new AuthurResponse().data(dataTable);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('user:add')")
    public void addUser(@Valid SystemUser user) throws AuthurException {
        try {
            this.userService.createUser(user);
        } catch (Exception e) {
            String message = "新增用户失败";
            log.error(message, e);
            throw new AuthurException(message);
        }
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('user:update')")
    public void updateUser(@Valid SystemUser user) throws AuthurException {
        try {
            this.userService.updateUser(user);
        } catch (Exception e) {
            String message = "修改用户失败";
            log.error(message, e);
            throw new AuthurException(message);
        }
    }
    @Validated
    @DeleteMapping("/{userIds}")
    @PreAuthorize("hasAnyAuthority('user:delete')")
    public void deleteUsers(@NotBlank(message = "{required}") @PathVariable String userIds) throws AuthurException {
        try {
            String[] ids = userIds.split(StringPool.COMMA);
            this.userService.deleteUsers(ids);
        } catch (Exception e) {
            String message = "删除用户失败";
            log.error(message, e);
            throw new AuthurException(message);
        }
    }

}
