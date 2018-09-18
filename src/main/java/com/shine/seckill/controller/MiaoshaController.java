package com.shine.seckill.controller;

import com.alibaba.druid.sql.visitor.functions.If;
import com.shine.seckill.model.MiaoshaOrder;
import com.shine.seckill.model.MiaoshaUser;
import com.shine.seckill.model.OrderInfo;
import com.shine.seckill.result.CodeMsg;
import com.shine.seckill.service.GoodsService;
import com.shine.seckill.service.MiaoshaService;
import com.shine.seckill.service.OrderService;
import com.shine.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Create By shine
 * 2018-09-18 21:16
 */
@Controller
@RequestMapping("/miaosha")
public class MiaoshaController {

    @Autowired
    GoodsService goodsService;

    @Autowired
    OrderService orderService;

    @Autowired
    MiaoshaService miaoshaService;

    @RequestMapping("/do_miaosha")
    public String doMiaosha(Model model, MiaoshaUser user,
    @RequestParam("goodsId")long goodsId){
        model.addAttribute("user", user);
        if (user==null) {
            return "login";
        }
        //判断库存
        GoodsVo goodsVo=goodsService.getGoodVoByGoodsId(goodsId);
        Integer stockCount = goodsVo.getStockCount();
        if (stockCount<=0) {
            model.addAttribute("errmsg", CodeMsg.MIAOSHA_OVER.getMsg());
            return "miaosha_fail";
        }

        //判断是否重复秒杀
        MiaoshaOrder order = orderService.getMaioshaOrderByUserIdGoodsId(user.getId(),goodsId);
        if (order!=null) {
            model.addAttribute("errmsg", CodeMsg.REPEATE_MIAOSHA.getMsg());
            return "miaosha_fail";
        }
        //减库存，下订单，写入秒杀订单
        OrderInfo orderInfo = miaoshaService.miaosha(user,goodsVo);
        model.addAttribute("orderInfo", orderInfo);
        model.addAttribute("goods", goodsVo);
        return "order_detail";
    }

}
