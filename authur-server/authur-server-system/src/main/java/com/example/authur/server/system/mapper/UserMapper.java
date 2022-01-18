package com.example.authur.server.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.authur.common.entity.SystemUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description:
 * @Author: jibing.Li
 * @Date: 2022/1/18 15:18
 */

@Mapper
public interface UserMapper extends BaseMapper<SystemUser> {

    /**
     * 查找用户详细信息
     * @param page 分页对象
     * @param user 用户对象，用于传递查询条件
     * @return Ipage
     */
    IPage<SystemUser> findUserDetailPage(Page page, @Param("user") SystemUser user);

}
