package com.example.authur.auth.manager;

import com.example.authur.auth.mapper.MenuMapper;
import com.example.authur.auth.mapper.UserMapper;
import com.example.authur.common.entity.Menu.Menu;
import com.example.authur.common.entity.SystemUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: jibing.Li
 * @Date: 2021/12/2 16:49
 */
@Service
public class UserManager {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MenuMapper menuMapper;

    public SystemUser findByName(String username){
        return userMapper.findByName(username);
    }

    public String findUserPermissions(String username){
        List<Menu> userPermissions = menuMapper.findUserPermissions(username);
        return userPermissions.stream().map(Menu::getPerms).collect(Collectors.joining(","));
    }
}
