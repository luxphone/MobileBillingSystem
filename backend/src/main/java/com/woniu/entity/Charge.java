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
 * @since 2023-06-03 12:17:46
 */
@Getter
@Setter
@TableName("t_charge")
public class Charge {

    @TableId("charge_code")
    private String chargeCode;

    @TableField("charge")
    private BigDecimal charge;


}
