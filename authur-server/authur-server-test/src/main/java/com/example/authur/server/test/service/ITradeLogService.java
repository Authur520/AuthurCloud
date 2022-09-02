package com.example.authur.server.test.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.authur.common.entity.system.TradeLog;

/**
 * @Description:
 * @Author: jibing.Li
 * @Date: 2022/9/1 17:25
 */
public interface ITradeLogService extends IService<TradeLog> {
    void packageAndSend(TradeLog tradeLog);
}
