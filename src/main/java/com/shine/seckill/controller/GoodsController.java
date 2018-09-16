package com.shine.seckill.controller;


import com.shine.seckill.model.MiaoshaUser;
import com.shine.seckill.redis.RedisService;
import com.shine.seckill.service.GoodsService;
import com.shine.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
        model.addAttribute("user", user);
        //查询商品列表
        List<GoodsVo> goodsList = goodsService.lsitGoodsVo();
        model.addAttribute("goodsList", goodsList);
        return "goods_list";
    }

    @RequestMapping("/to_detail/{goodsId}")
    public String detail(Model model, MiaoshaUser user,
                         @PathVariable("goodsId")long goodsId){
        model.addAttribute("user", user);
        //查询商品详情
        GoodsVo goods = goodsService.getGoodVoByGoodsId(goodsId);
        System.out.println(goods.toString());
        model.addAttribute("goods", goods);
        long startTime = goods.getStartDate().getTime();
        long endTime = goods.getEndDate().getTime();
        long now = System.currentTimeMillis();
        int miaoshaStatus = 0;
        int remainSeconds=0;
        if (startTime>now) {
            //秒杀未开始，倒计时
            miaoshaStatus=0;
            remainSeconds = (int) ((startTime - now) / 1000);
        } else if (endTime<now) {
            //秒杀已结束
            remainSeconds=-1;
            miaoshaStatus=2;
        }else{
            //秒杀进行中
            miaoshaStatus=1;
            remainSeconds=0;
        }
        model.addAttribute("miaoshaStatus", miaoshaStatus);
        model.addAttribute("remainSeconds", remainSeconds);
        return "goods_detail";
    }

}

