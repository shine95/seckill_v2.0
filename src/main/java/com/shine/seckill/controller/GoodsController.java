package com.shine.seckill.controller;


import com.shine.seckill.model.MiaoshaUser;
import com.shine.seckill.redis.RedisService;
import com.shine.seckill.service.GoodsService;
import com.shine.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Create By shine
 * 2018-09-07 14:01
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    GoodsService goodsService;

    @Autowired
    RedisService redisService;

    @RequestMapping("/to_list")
    public String list(Model model, MiaoshaUser user){
        System.out.println(user);
        model.addAttribute("user", user);
        //查询商品列表
        List<GoodsVo> goodsList = goodsService.lsitGoodsVo();
        model.addAttribute("goodsList", goodsList);
        return "goods_list";
    }

}
