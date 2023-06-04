package com.woniu.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author owl
 * @since 2023-06-03 12:17:45
 */
@Getter
@Setter
@TableName("t_account")
public class Account {

    @TableId("account_id")
    private Integer accountId;

    @TableField("contact_person")
    private String contactPerson;

    @TableField("contact_address")
    private String contactAddress;

    @TableField("account_balance")
    private BigDecimal accountBalance;


}
