package com.example.authur.server.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.authur.common.entity.system.TradeLog;

/**
 * @Description:
 * @Author: jibing.Li
 * @Date: 2022/9/1 17:09
 */
public interface ITradeLogService extends IService<TradeLog> {
    void orderAndPay(TradeLog tradeLog);

    void pay(TradeLog tradeLog, String transactionId);
}
