package com.woniu.entity;

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
@TableName("t_mobiles")
public class Mobiles {

    @TableId("mobile_number")
    private String mobileNumber;

    /**
     * STM或者UIM
     */
    @TableField("mobile_type")
    private String mobileType;

    @TableField("card_number")
    private String cardNumber;

    /**
     * 默认为'Y'
     */
    @TableField("is_available")
    private String isAvailable;


}
