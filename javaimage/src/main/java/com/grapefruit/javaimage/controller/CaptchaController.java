package com.grapefruit.javaimage.controller;

import com.google.code.kaptcha.Producer;
import com.grapefruit.javaimage.rsp.AjaxResult;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 验证码操作处理
 *
 * @author ruoyi
 */
@RestController
public class CaptchaController {
    private static final String imgdata = "data:image/gif;base64,";
    @Resource(name = "captchaProducerMath")
    private Producer captchaProducerMath;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    /**
     * 生成验证码
     */
    @GetMapping("/captchaImage")
    public AjaxResult getCode() {
        // 保存验证码信息
        String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 20);

        String capStr;
        BufferedImage image;

        // 生成验证码(String[] strings = capText.split("@"); 使用"@"分割字符串,得到算式和结果,结果存储用于后续交验)
        String capText = captchaProducerMath.createText();

        // 截取问题
        capStr = capText.substring(0, capText.lastIndexOf("@"));

        // 截取结果 并存入redis(key:questionId,valuse:resoult)
        String resoult = capText.split("@")[1];

        String questionId = UUID.randomUUID().toString().replace("-","");

        // 保存问题及时间(60秒)
        redisTemplate.opsForValue().set(questionId, resoult, 60, TimeUnit.SECONDS);
        image = captchaProducerMath.createImage(capStr);

        // 转换流信息写出
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        try {
            ImageIO.write(image, "jpg", os);
        } catch (IOException e) {
            return AjaxResult.error(e.getMessage());
        }

        AjaxResult ajax = AjaxResult.success();
        ajax.put("uuid", uuid);
        ajax.put("img", imgdata + Base64.encode(os.toByteArray()));
        ajax.put("qId",questionId);
        return ajax;
    }
}
