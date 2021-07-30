package com.grapefruit.javaimage.controller;

import com.grapefruit.javaimage.rsp.AjaxResult;
import com.grapefruit.javaimage.service.Img;
import com.grapefruit.javaimage.service.ImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 验证码操作处理
 *
 * @author ruoyi
 */
@RestController
public class CaptchaController {

    @Autowired
    ImgService imgService;

    /**
     * 生成验证码
     */
    @GetMapping("/captchaImage")
    public AjaxResult getCode() {
        AjaxResult ajax = AjaxResult.success();

        Img img = imgService.createImg();

        ajax.put("img", img.getImg());
        ajax.put("qId", img.getQId());

        return ajax;
    }
}
