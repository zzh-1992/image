/*
 *Copyright @2021 Grapefruit. All rights reserved.
 */

package com.grapefruit.javaimage.controller;

import com.grapefruit.javaimage.service.ImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * index
 *
 * @author zhihuangzhang
 * @version 1.0
 * @date 2021-06-12 7:12 下午
 */
@RestController
public class Index {

    @Autowired
    ImgService imgService;

    @GetMapping("/index")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("index");

        mv.getModel().put("img", imgService.createImg().getImg());
        return mv;
    }
}
