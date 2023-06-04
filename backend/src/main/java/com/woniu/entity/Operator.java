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
@TableName("t_operator")
public class Operator {

    @TableId("operator_id")
    private String operatorId;

    @TableField("operator_name")
    private String operatorName;

    @TableField("operator_pwd")
    private String operatorPwd;

    /**
     * 'Y'代表是管理员，'N'代表否，默认'N'
     */
    @TableField("is_admin")
    private String isAdmin;


}
