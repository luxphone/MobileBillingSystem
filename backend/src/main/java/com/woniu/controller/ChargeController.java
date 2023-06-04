package com.woniu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniu.entity.Charge;
import com.woniu.entity.ChargeRule;
import com.woniu.model.Result;
import com.woniu.service.ChargeRuleService;
import com.woniu.service.ChargeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author owl
 * @since 2023-06-03 12:17:46
 */
@RestController
@RequestMapping("/charge")
public class ChargeController {
    @Resource
    private ChargeService chargeService;
    @Resource
    private ChargeRuleService chargeRuleService;

    @GetMapping("/list")
    public Result list() {
        List<Charge> chargeList = chargeService.list();
        return Result.response(200, "ok", chargeList);
    }

    @PostMapping("/update")
    public Result update(Charge charge) {
        chargeService.saveOrUpdate(charge);
        return Result.response(200, "修改成功", null);
    }

    @GetMapping("/totalFee")
    public Result totalFee() {
        QueryWrapper<ChargeRule> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("func_id", "O");
        List<ChargeRule> chargeRuleList = chargeRuleService.list(queryWrapper);
        List<String> chargeCodeList = chargeRuleList.stream()
                .map(ChargeRule::getChargeCode)
                .collect(Collectors.toList());

        QueryWrapper<Charge> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.in("charge_code", chargeCodeList);
        List<Charge> chargeList = chargeService.list(queryWrapper1);

        BigDecimal total = chargeList.stream()
                .map(Charge::getCharge)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return Result.response(200, "ok", total);
    }

}
