package com.shine.seckill.service;

import com.shine.seckill.model.Goods;
import com.shine.seckill.model.MiaoshaUser;
import com.shine.seckill.model.OrderInfo;
import com.shine.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Create By shine
 * 2018-09-18 21:33
 */
@Service
public class MiaoshaService {

    @Autowired
    GoodsService goodsService;

    @Autowired
    OrderService orderService;

    @Transactional
    public OrderInfo miaosha(MiaoshaUser user, GoodsVo goods) {
        //减库存
        goodsService.reduceStock(goods);
        //写订单
        return orderService.createOrder(user,goods);
    }
}
