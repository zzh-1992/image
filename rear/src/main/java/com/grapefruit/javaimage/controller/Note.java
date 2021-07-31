/*
 *Copyright @2021 Grapefruit. All rights reserved.
 */

package com.grapefruit.javaimage.controller;

import com.grapefruit.javaimage.req.LoginReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

/**
 * 短信验证码处理
 *
 * @author zhihuangzhang
 * @version 1.0
 * @date 2021-07-30 9:35 下午
 */
@RestController
@RequestMapping("/")
public class Note {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @PostMapping("/note")
    public String getNote(@RequestBody LoginReq req) {

        String phone = req.getPhone();

        StringBuilder sb = new StringBuilder();
        SecureRandom random = new SecureRandom();
        for (int i = 0; i <= 3; i++) {
            int num = random.nextInt(9);
            sb.append(num);
        }
        String note = sb.toString();
        redisTemplate.opsForValue().set(phone, note, 30, TimeUnit.SECONDS);
        return note;
        // 生成phone-4位随机数
        // 调用第三方接口发送消息
        // 保存phone 4位随机数到redis
    }
}
