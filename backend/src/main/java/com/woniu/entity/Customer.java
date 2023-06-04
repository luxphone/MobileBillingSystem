package com.woniu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
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
@TableName("t_customer")
public class Customer {

    @TableId(value = "customer_id", type = IdType.AUTO)
    private Integer customerId;

    /**
     * 目前只考虑：居民身份证（D），军官证（A），护照（P）
     */
    @TableField("id_type")
    private String idType;

    @TableField("id_number")
    private String idNumber;

    @TableField("customer_name")
    private String customerName;

    @TableField("customer_birthday")
    private Date customerBirthday;

    @TableField("customer_sex")
    private String customerSex;

    @TableField("customer_address")
    private String customerAddress;


}
