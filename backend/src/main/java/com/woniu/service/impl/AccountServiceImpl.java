package com.woniu.service.impl;

import com.woniu.entity.Account;
import com.woniu.mapper.AccountMapper;
import com.woniu.service.AccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author owl
 * @since 2023-06-03 12:17:45
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

}
