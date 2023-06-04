package com.woniu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author owl
 * @since 2023-06-03 12:17:46
 */
@Getter
@Setter
@TableName("t_user")
public class User {

    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    @TableField("mobile_number")
    private String mobileNumber;

    @TableField("roaming_status")
    private String roamingStatus;

    @TableField("com_level")
    private String comLevel;

    @TableField("customer_id")
    private Integer customerId;

    @TableField("account_id")
    private Integer accountId;


}
