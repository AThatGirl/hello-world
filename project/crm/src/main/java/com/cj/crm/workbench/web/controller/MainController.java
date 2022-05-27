package com.cj.crm.workbench.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * main
 *
 * @author 杰瑞
 * @date 2022/05/14
 */

@Controller
public class MainController {

    @RequestMapping("/workbench/main/index.do")
    public String index(){
        //跳转到main/index.jsp
        return "workbench/main/index";
    }

}
