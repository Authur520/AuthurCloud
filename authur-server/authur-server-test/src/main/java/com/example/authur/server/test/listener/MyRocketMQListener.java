package com.example.authur.server.test.listener;

import com.example.authur.common.entity.system.TradeLog;
import com.example.authur.server.test.service.ITradeLogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: jibing.Li
 * @Date: 2022/9/1 17:30
 */
@Slf4j
@Component
@RocketMQMessageListener(consumerGroup = "test-group", topic = "pay-success")
public class MyRocketMQListener implements RocketMQListener<TradeLog> {

    @Autowired
    ITradeLogService iTradeLogService;

    @Override
    public void onMessage(TradeLog tradeLog) {
        log.info("监听到用户已经下单并支付成功ID为{}，名称为{}的商品", tradeLog.getGoodsId(), tradeLog.getGoodsName());
        iTradeLogService.packageAndSend(tradeLog);
    }
}
