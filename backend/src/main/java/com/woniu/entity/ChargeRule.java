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
@TableName("t_charge_rule")
public class ChargeRule {

    @TableId("func_id")
    private String funcId;

    @TableField("charge_code")
    private String chargeCode;


}
