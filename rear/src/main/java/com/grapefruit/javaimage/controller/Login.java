/*
 *Copyright @2021 Grapefruit. All rights reserved.
 */

package com.grapefruit.javaimage.controller;

import com.grapefruit.javaimage.entity.User;
import com.grapefruit.javaimage.req.LoginReq;
import com.grapefruit.javaimage.rsp.AjaxResult;
import com.grapefruit.javaimage.service.UserService;
import com.grapefruit.utils.security.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * 登陆处理
 *
 * @author zhihuangzhang
 * @version 1.0
 * @date 2021-07-31 1:37 下午
 */
@RestController
@RequestMapping("/")
public class Login {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private UserService userService;

    @PostMapping(value = "/login")
    public AjaxResult login(@RequestBody @Validated LoginReq req) throws NoSuchAlgorithmException, IOException,
            InvalidKeySpecException {
        AjaxResult ajax = new AjaxResult();

        // 判断是否有该用户(暂时不处理)
        String phone = req.getPhone();
        String password = req.getPassword();
        String note = req.getNote();

        // 从redis获取验证码
        String cacheNote = redisTemplate.opsForValue().get(phone);
        if (cacheNote == null || !cacheNote.equals(note)) {
            // TODO 验证码无效
            ajax.put("error", "验证码无效");
            ajax.put("code", "0");
            return ajax;
        }

        // 从数据库获取用户信息
        User user = userService.selectUserByPhone(phone);
        if (user == null) {
            ajax.put("error", "该用户没有注册");
            ajax.put("code", "0");
            return ajax;
        }
        if (!user.getPassword().equals(password)) {
            ajax.put("error", "该用户密码错误");
            ajax.put("code", "0");
            return ajax;
        }

        // 使用账户及密码生成token  userName password uuid
        String token = TokenUtils.generateTokenWithRSA512(phone, password, 30 * 60L);

        // 返回token
        ajax.put("phone", phone);
        ajax.put("token", token);
        ajax.put("code", "1");
        return ajax;
    }
}
