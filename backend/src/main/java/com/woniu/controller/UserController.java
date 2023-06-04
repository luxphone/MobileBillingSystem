package com.woniu.controller;

import com.woniu.entity.User;
import com.woniu.model.Result;
import com.woniu.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author owl
 * @since 2023-06-03 12:17:46
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    UserService userService;

    @PostMapping("/add")
    public Result add(User user) {
        userService.save(user);
        return Result.response(200, "添加成功", null);
    }
}

