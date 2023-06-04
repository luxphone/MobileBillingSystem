package com.woniu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniu.entity.ChargeRule;
import com.woniu.model.Result;
import com.woniu.service.ChargeRuleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
@RequestMapping("/chargeRule")
public class ChargeRuleController {
    @Resource
    ChargeRuleService chargeRuleService;

    /**
     * 获取所有收费规则
     *
     * @return
     */
    @GetMapping("/list")
    public Result list() {
        List<ChargeRule> chargeRuleList = chargeRuleService.list();
        List<String> chargeFuncList = chargeRuleList.stream().map(ChargeRule::getFuncId).distinct().collect(Collectors.toList());
        return Result.response(200, "ok", chargeFuncList);
    }

    /**
     * 获取收费规则的收费条目
     *
     * @param funcId
     * @return
     */
    @GetMapping("/query")
    public Result query(String funcId) {
        QueryWrapper<ChargeRule> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("func_id", funcId);
        List<ChargeRule> chargeRuleList = chargeRuleService.list(queryWrapper);
        List<String> chargeCodeList = chargeRuleList.stream().map(ChargeRule::getChargeCode).collect(Collectors.toList());
        return Result.response(200, "ok", chargeCodeList);
    }

    /**
     * 更新业务收费项目
     * @param funcId
     * @param chargeCodeList
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestParam("funcId") String funcId, @RequestParam("chargeCodeList[]") List<String> chargeCodeList) {
        QueryWrapper<ChargeRule> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("func_id", funcId);
        List<ChargeRule> chargeRuleList = chargeRuleService.list(queryWrapper);
        List<String> oldChargeCodeList = chargeRuleList.stream().map(ChargeRule::getChargeCode).collect(Collectors.toList());
        List<ChargeRule> newChargeRuleList = new ArrayList<>();
        if (chargeCodeList.size() > oldChargeCodeList.size()) {
            for (String chargeCode : chargeCodeList) {
                if (!oldChargeCodeList.contains(chargeCode)) {
                    ChargeRule chargeRule = new ChargeRule();
                    chargeRule.setFuncId(funcId);
                    chargeRule.setChargeCode(chargeCode);
                    newChargeRuleList.add(chargeRule);
                }
            }
            chargeRuleService.saveBatch(newChargeRuleList);
        } else {
            for (String chargeCode : oldChargeCodeList) {
                if (!chargeCodeList.contains(chargeCode)) {
                    ChargeRule chargeRule = new ChargeRule();
                    chargeRule.setFuncId(funcId);
                    chargeRule.setChargeCode(chargeCode);
                    newChargeRuleList.add(chargeRule);
                }
            }
            List<String> funcIdList = newChargeRuleList.stream().map(ChargeRule::getFuncId).collect(Collectors.toList());
            List<String> chargeCodeList1 = newChargeRuleList.stream().map(ChargeRule::getChargeCode).collect(Collectors.toList());

            QueryWrapper<ChargeRule> queryWrapper1 = new QueryWrapper<>();
            queryWrapper.in("func_id", funcIdList)
                    .in("charge_code", chargeCodeList1);

            chargeRuleService.remove(queryWrapper);
        }
        return Result.response(200, "修改成功", null);
    }
}


