package com.woniu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.code.kaptcha.Producer;
import com.woniu.entity.Operator;
import com.woniu.model.Result;
import com.woniu.service.OperatorService;
import com.woniu.utils.JwtUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author owl
 * @since 2023-06-03 12:17:46
 */
@RestController
@RequestMapping("/operator")
public class OperatorController {
    @Resource
    Producer producer;
    @Resource
    OperatorService operatorService;
    @Resource
    StringRedisTemplate stringRedisTemplate;

    /**
     * 登录时获取验证码
     *
     * @param request
     * @param response
     */
    @GetMapping("/getLoginCode")
    public void getLoginCode(HttpServletRequest request, HttpServletResponse response) {
        String code = producer.createText();
        request.getSession().setAttribute("loginCode", code);
        BufferedImage bufferedImage = producer.createImage(code);
        response.setContentType("image/jpeg");
        try {
            ImageIO.write(bufferedImage, "jpg", response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 登录
     *
     * @param username
     * @param password
     * @param code
     * @param request
     * @param response
     * @return
     */
    @PostMapping("/login")
    public Result login(String username, String password, String code, HttpServletRequest request, HttpServletResponse response) {
        String loginCode = (String) request.getSession().getAttribute("loginCode");
        if (loginCode == null || !loginCode.equalsIgnoreCase(code)) {
            return Result.response(-1, "验证码错误", null);
        }
        QueryWrapper<Operator> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("operator_id", username);
        Operator operator = operatorService.getOne(queryWrapper);
        if (operator == null) {
            return Result.response(-1, "用户名不存在", null);
        }
        if (!operator.getOperatorPwd().equals(password)) {
            return Result.response(-1, "密码错误", null);
        }
        String token = JwtUtils.generateToken(username);
        stringRedisTemplate.opsForValue().set(token, username, 1L, TimeUnit.DAYS);
        return Result.response(200, "登录成功", token);
    }

    /**
     * 从请求头中获取用户名
     *
     * @param request
     * @return
     */
    public String getUsernameFromRequest(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        String token = authorization.replace("Bearer ", "");
        return stringRedisTemplate.opsForValue().get(token);
    }

    /**
     * 从请求头中获取token
     *
     * @param request
     * @return
     */
    public String getTokenFromRequest(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        return authorization.replace("Bearer ", "");
    }

    /**
     * 退出登录
     *
     * @param request
     * @return
     */
    @GetMapping("/logout")
    public Result logout(HttpServletRequest request) {
        String token = getTokenFromRequest(request);
        stringRedisTemplate.delete(token);
        return Result.response(200, "退出成功", null);
    }

    /**
     * 获取登录用户基本信息
     *
     * @param request
     * @return
     */
    @GetMapping("/info")
    public Result info(HttpServletRequest request) {
        String username = getUsernameFromRequest(request);
        QueryWrapper<Operator> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("operator_id", username);
        Operator operator = operatorService.getOne(queryWrapper);
        return Result.response(200, "ok", operator);
    }

    /**
     * 获取所有用户信息
     *
     * @return
     */
    @GetMapping("/list")
    public Result list(Integer current, Integer size) {
        Page<Operator> page = new Page<>(current, size);
        return Result.response(200, "ok", operatorService.page(page));
    }

    /**
     * 根据用户编号和姓名获取所有用户信息
     *
     * @param operatorId
     * @param operatorName
     * @return
     */
    @PostMapping("/search")
    public Result search(Integer current, Integer size, String operatorId, String operatorName) {
        Page<Operator> page = new Page<>(current, size);
        QueryWrapper<Operator> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("operator_id", operatorId).or().like("operator_name", operatorName);
        return Result.response(200, "ok", operatorService.page(page, queryWrapper));
    }

    /**
     * 添加新操作员
     *
     * @param operator
     * @return
     */
    @PostMapping("/add")
    public Result add(Operator operator) {
        return Result.response(200, "添加成功", operatorService.save(operator));
    }

    /**
     * 修改操作员信息
     *
     * @param operator
     * @return
     */
    @PostMapping("/edit")
    public Result edit(Operator operator) {
        return Result.response(200, "修改成功", operatorService.saveOrUpdate(operator));
    }

    /**
     * 根据id删除操作员
     *
     * @param operatorId
     * @return
     */
    @DeleteMapping("/delete")
    public Result delete(String operatorId) {
        return Result.response(200, "删除成功", operatorService.removeById(operatorId));
    }

}

