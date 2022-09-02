package com.example.authur.server.system.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.authur.common.entity.system.TradeLog;
import com.example.authur.server.system.mapper.TradeLogMapper;
import com.example.authur.server.system.service.ITradeLogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Description:
 * @Author: jibing.Li
 * @Date: 2022/9/1 17:12
 */
@Slf4j
@Service("tradeLogService")
public class TradeLogServiceImpl extends ServiceImpl<TradeLogMapper,TradeLog> implements ITradeLogService {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Override
    @Transactional
    public void orderAndPay(TradeLog tradeLog) {
        tradeLog.setCreateTime(new Date());
        tradeLog.setStatus("下单并成功支付！");

        this.save(tradeLog);
        log.info("用户已经下单并支付成功商品ID为{}，名称为{}的商品", tradeLog.getGoodsId(), tradeLog.getGoodsName());

        this.rocketMQTemplate.convertAndSend("pay-success",tradeLog);
    }
}
