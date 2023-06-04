package com.woniu.controller;

import com.woniu.entity.Mobiles;
import com.woniu.model.Result;
import com.woniu.service.MobilesService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author owl
 * @since 2023-06-03 12:17:46
 */
@RestController
@RequestMapping("/mobiles")
public class MobilesController {
    @Resource
    MobilesService mobilesService;

    /**
     * 批量新增号段
     *
     * @param mobileType        号码类型 SIM or UIM
     * @param mobileNumberLeft  号段起始（包含）
     * @param mobileNumberRight 号段结束（包含）
     * @return
     */
    @PostMapping("/add")
    public Result add(String mobileType, Integer mobileNumberLeft, Integer mobileNumberRight) {
        List<Mobiles> mobilesList = new ArrayList<>();
        for (int i = mobileNumberLeft; i <= mobileNumberRight; i++) {
            Mobiles mobiles = new Mobiles();
            mobiles.setMobileNumber(String.valueOf(i));
            mobiles.setMobileType(mobileType);
            String timestamp = String.valueOf(System.currentTimeMillis());
            mobiles.setCardNumber(timestamp);
            mobilesList.add(mobiles);
        }
        mobilesService.saveBatch(mobilesList);
        Map<String, String> data = new HashMap<>();
        data.put("range", mobileNumberLeft + "-" + mobileNumberRight);
        return Result.response(200, "添加成功", data);
    }
}

