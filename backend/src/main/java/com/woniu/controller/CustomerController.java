package com.woniu.controller;


import com.woniu.entity.Customer;
import com.woniu.model.Result;
import com.woniu.service.CustomerService;
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
@RequestMapping("/customer")
public class CustomerController {
    @Resource
    CustomerService customerService;

    @PostMapping("/add")
    public Result add(Customer customer) {
        customerService.save(customer);
        return Result.response(200, "添加成功", customer.getCustomerId());
    }
}

