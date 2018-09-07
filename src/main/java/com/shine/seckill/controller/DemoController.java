package com.shine.seckill.controller;

import com.shine.seckill.model.User;
import com.shine.seckill.redis.KeyPrefix;
import com.shine.seckill.redis.RedisService;
import com.shine.seckill.redis.UserKey;
import com.shine.seckill.result.CodeMsg;
import com.shine.seckill.result.Result;
import com.shine.seckill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    UserService userService;

    @Autowired
    RedisService redisService;

    @RequestMapping("/")
    @ResponseBody
    public String home(){
        return "Hello World!";
    }

    @RequestMapping("/say")
    @ResponseBody
    public Result<String> hello(){
        return Result.success("Hello Shine!");
    }

    @RequestMapping("/sayError")
    @ResponseBody
    public Result<String> helloError(){
        return Result.error(CodeMsg.SERVER_ERROR);
    }

    @RequestMapping("/thymeleaf")
    public String thymeleaf(Model model){
        model.addAttribute("name", "name");
        return "hello";
    }

    @RequestMapping("/db/get")
    @ResponseBody
    public Result<User> dbGet(){
        User user = userService.getById(100);
        return Result.success(user);
    }

    @RequestMapping("/db/tx")
    @ResponseBody
    public Result<Boolean> dbTx(){
        userService.tx();
        return Result.success(true);
    }

    @RequestMapping("/redis/get")
    @ResponseBody
    public Result<User> redisGet(){
        User  user = redisService.get(UserKey.getById,""+1, User.class);
        return Result.success(user);
    }

    @RequestMapping("/redis/set")
    @ResponseBody
    public Result<Boolean> redisSet(){
        User user = new User(1, "shine");
        redisService.set(UserKey.getById,""+1, user);
        return Result.success(true);
    }

}
