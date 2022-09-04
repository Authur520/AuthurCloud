package com.example.authur.common.entity.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author authur
 * @description:
 */
@Data
@TableName("t_transaction_log")
public class TransactionLog implements Serializable {

    private static final long serialVersionUID = 1268216478456291093L;

    @TableId(value = "ID",type = IdType.AUTO)
    private Long id;
    @TableField("TRANSACTION_ID")
    private String transactionId;
    @TableField("REMARK")
    private String remark;
}
