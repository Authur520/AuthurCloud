package com.example.authur.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.authur.common.entity.Menu.Menu;

import java.util.List;

/**
 * @Description:
 * @Author: jibing.Li
 * @Date: 2021/12/2 10:26
 */
public interface MenuMapper extends BaseMapper<Menu> {
    List<Menu> findUserPermissions(String username);
}
