package com.cj.crm.workbench.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 跳转到业务台的主页面
 *
 * @author 杰瑞
 * @date 2022/05/12
 */
@Controller
public class WorkBenchIndexController {

    @RequestMapping("/workbench/index.do")
    public String index(){
        return "workbench/index";
    }

}
