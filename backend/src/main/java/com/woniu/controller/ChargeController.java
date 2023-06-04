package com.woniu.controller;


import com.woniu.entity.Charge;
import com.woniu.model.Result;
import com.woniu.service.ChargeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

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
}
