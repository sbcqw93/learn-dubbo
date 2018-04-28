package com.ilearn.dubbo.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by George on 2018/2/2 0002.
 */
@Controller
public class IndexController extends BaseController {

    @RequestMapping(value = "index")
    public String index(HttpServletRequest request, HttpServletResponse response) {
        return "index";
    }

    @ResponseBody
    @RequestMapping(value = "a")
    public String a(HttpServletRequest request, HttpServletResponse response) {
        return "index";
    }
}
