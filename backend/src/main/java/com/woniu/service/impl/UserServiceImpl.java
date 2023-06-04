package com.woniu.service.impl;

import com.woniu.entity.User;
import com.woniu.mapper.UserMapper;
import com.woniu.service.UserService;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
