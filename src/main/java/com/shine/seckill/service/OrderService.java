package com.shine.seckill.service;

import com.shine.seckill.dao.OrderDao;
import com.shine.seckill.model.MiaoshaOrder;
import com.shine.seckill.model.MiaoshaUser;
import com.shine.seckill.model.OrderInfo;
import com.shine.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Create By shine
 * 2018-09-18 21:26
 */
@Service
public class OrderService{

        @Autowired
        OrderDao orderDao;

        public MiaoshaOrder getMaioshaOrderByUserIdGoodsId(long userId, long goodsId) {
            return orderDao.getMaioshaOrderByUserIdGoodsId(userId, goodsId);
        }

        @Transactional
        public OrderInfo createOrder(MiaoshaUser user, GoodsVo goods) {
            OrderInfo orderInfo = new OrderInfo();
            orderInfo.setCreateDate(new Date());
            orderInfo.setDeliveryAddrId(0L);
            orderInfo.setGoodsCount(1);
            orderInfo.setId(goods.getId());
            orderInfo.setGoodsName(goods.getGoodsName());
            orderInfo.setGoodsPrice(goods.getMiaoshaPrice());
            orderInfo.setOrderChannel(1);
            orderInfo.setStatus(0);
            orderInfo.setUserId(user.getId());
            orderDao.insert(orderInfo);
            MiaoshaOrder miaoshaOrder = new MiaoshaOrder();
            miaoshaOrder.setGoodsId(goods.getId());
            miaoshaOrder.setOrderId(orderInfo.getId());
            miaoshaOrder.setUserId(user.getId());
            orderDao.insertMiaoshaOrder(miaoshaOrder);
            return orderInfo;
        }
}
