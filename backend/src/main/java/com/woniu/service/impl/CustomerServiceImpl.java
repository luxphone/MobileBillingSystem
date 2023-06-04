package com.woniu.service.impl;

import com.woniu.entity.Customer;
import com.woniu.mapper.CustomerMapper;
import com.woniu.service.CustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author owl
 * @since 2023-06-03 12:17:46
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {

}
