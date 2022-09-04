package com.example.authur.server.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.authur.common.entity.system.TradeLog;
import com.example.authur.common.entity.system.TransactionLog;
import com.example.authur.server.system.mapper.TradeLogMapper;
import com.example.authur.server.system.mapper.TransactionLogMapper;
import com.example.authur.server.system.service.ITradeLogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

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
    @Autowired
    TransactionLogMapper transactionLogMapper;

    @Override
    public void orderAndPay(TradeLog tradeLog) {
        // 监测库存
        log.info("检测商品Id为{}，名称为{}的商品库存，库存充足",tradeLog.getGoodsId(),tradeLog.getGoodsName());

        String transactionId = UUID.randomUUID().toString();
        // 往RocketMQ中发送事务消息
        // this.rocketMQTemplate.convertAndSend("pay-success", tradeLog);
        this.rocketMQTemplate.sendMessageInTransaction(
          "pay-success-group", //事务消息分组
                "pay-success",   //事务消息topic
                MessageBuilder.withPayload(tradeLog)
                        .setHeader(RocketMQHeaders.TRANSACTION_ID, transactionId)
                        .build(), //消息
                tradeLog //额外信息
        );
    }

    @Override
    @Transactional
    public void pay(TradeLog tradeLog, String transactionId) {
        tradeLog.setCreateTime(new Date());
        tradeLog.setStatus("下单并支付成功");
        // 保存支付日志
        this.save(tradeLog);
        log.info("用户已经下单并支付成功商品ID为{}，名称为{}的商品", tradeLog.getGoodsId(), tradeLog.getGoodsName());
        // 记录事务日志
        TransactionLog transactionLog = new TransactionLog();
        transactionLog.setTransactionId(transactionId);
        String remark = String.format("事务ID为%s的本地事务执行成功", transactionId);
        transactionLog.setRemark(remark);
        this.transactionLogMapper.insert(transactionLog);
        log.info("事务ID为{}的本地事务执行成功",transactionId);

    }
}
