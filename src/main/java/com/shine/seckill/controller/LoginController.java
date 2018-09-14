package com.shine.seckill.controller;

import com.shine.seckill.result.Result;
import com.shine.seckill.service.MiaoshaUserService;
import com.shine.seckill.vo.LoginVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    MiaoshaUserService userService;

    private static Logger log = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping("/to_login")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/do_login")
    @ResponseBody
    public Result<Boolean> doLogin( HttpServletResponse response, @Valid LoginVo loginVo){
        log.info(loginVo.toString());
        //登录
        boolean login = userService.login(response, loginVo);
        long id = Long.valueOf(loginVo.getMobile());
        if (login) {
            int i = userService.setLoginCount(id);
            if (i>0) {
                System.out.println("添加登录次数成功！");
            }
        }
        return Result.success(true);
    }

}
