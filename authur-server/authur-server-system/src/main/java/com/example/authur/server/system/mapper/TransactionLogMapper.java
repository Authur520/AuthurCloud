package com.example.authur.server.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.authur.common.entity.system.TransactionLog;
import org.mapstruct.Mapper;

/**
 * @author authur
 * @description:
 */
@Mapper
public interface TransactionLogMapper extends BaseMapper<TransactionLog> {
}
