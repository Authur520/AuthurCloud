package com.example.authur.server.system.listener;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.authur.common.entity.system.TradeLog;
import com.example.authur.common.entity.system.TransactionLog;
import com.example.authur.server.system.mapper.TransactionLogMapper;
import com.example.authur.server.system.service.ITradeLogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

/**
 * @author authur
 * @description:
 */
@Slf4j
@Component
@RocketMQTransactionListener(txProducerGroup = "pay-success-group")
public class MyRocketMQListener implements RocketMQLocalTransactionListener {

    @Autowired
    ITradeLogService iTradeLogService;
    @Autowired
    TransactionLogMapper transactionLogMapper;

    /**
     * 执行本地事务
     * @param message 消息
     * @param o 额外参数
     * @return RocketMQ事务状态
     */
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {
        MessageHeaders headers = message.getHeaders();
        String transactionId = (String) headers.get(RocketMQHeaders.TRANSACTION_ID);
        try {
            TradeLog tradeLog = (TradeLog)o;
            this.iTradeLogService.pay(tradeLog,transactionId);
            log.info("本地事务执行成功，往RocketMQ发送COMMIT");
            return RocketMQLocalTransactionState.COMMIT;
        }catch (Exception e){
            log.info("本地事务回滚，往RocketMQ发送ROLLBACK", e);
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }

    /**
     * RocketMQ回查本地事务状态
     * @param message 消息
     * @return RocketMQ事务状态
     */
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        MessageHeaders headers = message.getHeaders();
        String transactionId = (String) headers.get(RocketMQHeaders.TRANSACTION_ID);
        log.info("RocketMQ事务状态回查");
        // 根据数据库中的事务id查询对应的事务日志
        TransactionLog transactionLog = transactionLogMapper.selectOne(
                new LambdaQueryWrapper<TransactionLog>().eq(TransactionLog::getTransactionId, transactionId)
        );
        return transactionLog != null ? RocketMQLocalTransactionState.COMMIT : RocketMQLocalTransactionState.ROLLBACK;
    }
}
