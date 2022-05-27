package com.cj.crm.settings.web.controller;
//系统管理的所有controller

import com.cj.crm.commons.constants.Constants;
import com.cj.crm.commons.domain.ReturnObject;
import com.cj.crm.commons.utils.DateUtil;
import com.cj.crm.settings.domain.User;
import com.cj.crm.settings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户controller
 *
 * @author 杰瑞
 * @date 2022/05/10
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;
//    RequestMapping路径和响应返回的页面的资源目录保持一致
    /**
     * 登录
     *
     * @return {@link String}
     */
    @RequestMapping("/settings/qx/user/toLogin.do")
    public  String toLogin(){
        //请求转发到登录页面
        return "settings/qx/user/login";
    }

    @RequestMapping("/settings/qx/user/login.do")
    @ResponseBody
    public Object login(String loginAct, String loginPwd, String isRemPwd, HttpServletRequest request, HttpSession session,HttpServletResponse response){
//        封装参数
        Map<String,Object> map=new HashMap<>();
        map.put("loginAct",loginAct);
        map.put("loginPwd",loginPwd);
        User user = userService.queryUserByLoginActAndPwd(map);
//        根据查询结果生成响应信息
        ReturnObject returnObject=new ReturnObject();
        if (user==null){
            //登录失败，用户名或者密码错误
            returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("用户名或密码错误");
        }else {
            //进一步判断账号是否合法
            String nowStr = DateUtil.formatDateTime(new Date());
            if (nowStr.compareTo(user.getExpireTime())>0){
                //登录失败，账号已经过期
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("账号已经过期");
            }else if ("0".equals(user.getLockState())){
                //登录失败，状态被锁定
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("状态被锁定");
            }else if (user.getAllowIps().contains(request.getRemoteAddr())){
                //登录失败，ip不为常用ip
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("ip受限");
            }else {
                //登录成功
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_SUCCESS);
                //把user对象保存到session域中
                session.setAttribute(Constants.SESSION_USER,user);
                //如果需要记住密码，则往浏览器写cookie
                //cookie中添加账号和密码
                Cookie actCookie=new Cookie("loginAct",user.getLoginAct());
                Cookie pwdCookie=new Cookie("loginPwd",user.getLoginPwd());
                if ("true".equals(isRemPwd)){
                    //设置十天后销毁cookie
                    actCookie.setMaxAge(10*24*60*60);
                    pwdCookie.setMaxAge(10*24*60*60);
                }else {
                    //把没有过期的cookie删除
                    actCookie.setMaxAge(0);
                    pwdCookie.setMaxAge(0);
                }
                response.addCookie(actCookie);
                response.addCookie(pwdCookie);
            }
        }
        return returnObject;
    }

    @RequestMapping("/settings/qx/user/logout.do")
    public String logout(HttpSession session,HttpServletResponse response){

        //退出登录后将cookie和session都销毁
        Cookie actCookie=new Cookie("loginAct","");
        Cookie pwdCookie=new Cookie("loginPwd","");
        actCookie.setMaxAge(0);
        pwdCookie.setMaxAge(0);
        response.addCookie(actCookie);
        response.addCookie(pwdCookie);

        session.invalidate();
        return "redirect:/";
    }

}
