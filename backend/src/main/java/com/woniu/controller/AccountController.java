package com.woniu.controller;


import com.woniu.entity.Account;
import com.woniu.model.Result;
import com.woniu.service.AccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author owl
 * @since 2023-06-03 12:17:45
 */
@RestController
@RequestMapping("/account")
public class AccountController {
    @Resource
    AccountService accountService;
    @GetMapping("/query")
    public Result query(Integer accountId) {
        Account account = accountService.getById(accountId);
        return Result.response(200, "ok", account);
    }
    @PostMapping("/add")
    public Result add(Account account) {
        accountService.save(account);
        return Result.response(200, "添加成功", null);
    }

    @PostMapping("/update")
    public Result update(Account account) {
        accountService.saveOrUpdate(account);
        return Result.response(200, "修改成功", null);
    }
}

