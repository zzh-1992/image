package com.grapefruit.javaimage.controller;

import com.google.code.kaptcha.Producer;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

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
        capStr = capText.substring(0, capText.lastIndexOf("@"));
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
        return ajax;
    }

    @Getter
    @Setter
    public static class AjaxResult extends HashMap<String, Object> {

        /**
         * 状态码
         */
        public static final String CODE_TAG = "code";
        /**
         * 返回内容
         */
        public static final String MSG_TAG = "msg";
        /**
         * 数据对象
         */
        public static final String DATA_TAG = "data";
        private static final long serialVersionUID = 1L;

        /**
         * 初始化一个新创建的 AjaxResult 对象，使其表示一个空消息。
         */
        public AjaxResult() {
        }

        /**
         * 初始化一个新创建的 AjaxResult 对象
         *
         * @param code 状态码
         * @param msg  返回内容
         */
        public AjaxResult(int code, String msg) {
            super.put(CODE_TAG, code);
            super.put(MSG_TAG, msg);
        }

        /**
         * 初始化一个新创建的 AjaxResult 对象
         *
         * @param code 状态码
         * @param msg  返回内容
         * @param data 数据对象
         */
        public AjaxResult(int code, String msg, Object data) {
            super.put(CODE_TAG, code);
            super.put(MSG_TAG, msg);
            if (ObjectUtils.isNotEmpty(data)) {
                super.put(DATA_TAG, data);
            }
        }

        /**
         * 返回成功消息
         *
         * @return 成功消息
         */
        public static AjaxResult success() {
            return AjaxResult.success("操作成功");
        }

        /**
         * 返回成功数据
         *
         * @return 成功消息
         */
        public static AjaxResult success(Object data) {
            return AjaxResult.success("操作成功", data);
        }

        /**
         * 返回成功消息
         *
         * @param msg 返回内容
         * @return 成功消息
         */
        public static AjaxResult success(String msg) {
            return AjaxResult.success(msg, null);
        }

        /**
         * 返回成功消息
         *
         * @param msg  返回内容
         * @param data 数据对象
         * @return 成功消息
         */
        public static AjaxResult success(String msg, Object data) {
            return new AjaxResult(0, msg, data);
        }

        /**
         * 返回错误消息
         *
         * @return
         */
        public static AjaxResult error() {
            return AjaxResult.error("操作失败");
        }

        /**
         * 返回错误消息
         *
         * @param msg 返回内容
         * @return 警告消息
         */
        public static AjaxResult error(String msg) {
            return AjaxResult.error(msg, null);
        }

        /**
         * 返回错误消息
         *
         * @param msg  返回内容
         * @param data 数据对象
         * @return 警告消息
         */
        public static AjaxResult error(String msg, Object data) {
            return new AjaxResult(1, msg, data);
        }

        /**
         * 返回错误消息
         *
         * @param code 状态码
         * @param msg  返回内容
         * @return 警告消息
         */
        public static AjaxResult error(int code, String msg) {
            return new AjaxResult(code, msg, null);
        }
    }
}
