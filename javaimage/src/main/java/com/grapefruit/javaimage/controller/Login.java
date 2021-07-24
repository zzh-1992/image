/*
 *Copyright @2021 Grapefruit. All rights reserved.
 */

package com.grapefruit.javaimage.controller;

import com.grapefruit.javaimage.req.LoginReq;
import com.grapefruit.javaimage.rsp.AjaxResult;
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
 * @date 2021-06-12 7:12 下午
 */
@RestController
@RequestMapping("/")
public class Login {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @PostMapping(value = "/login")
    public AjaxResult login(@RequestBody @Validated LoginReq req) throws NoSuchAlgorithmException, IOException,
            InvalidKeySpecException {
        AjaxResult ajax = new AjaxResult();

        // 判断是否有该用户(暂时不处理)
        String account = req.getAccount();
        String password = req.getPassword();

        // 使用账户及密码生成token  userName password uuid
        String token = TokenUtils.generateTokenWithRSA512(account, password, 30 * 60L);

        // 返回token
        ajax.put("token", token);

        String resoult = req.getResoult();
        String questionId = req.getQuestionId();
        String cachingResoult = redisTemplate.opsForValue().get(questionId);

        // 异常情况暂时不处理

        return ajax;
    }
}
