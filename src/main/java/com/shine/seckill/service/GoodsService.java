package com.shine.seckill.service;

import com.shine.seckill.dao.GoodDao;
import com.shine.seckill.model.Goods;
import com.shine.seckill.model.MiaoshaGoods;
import com.shine.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsService {

    @Autowired
    GoodDao goodDao;

    public List<GoodsVo> lsitGoodsVo() {
        return goodDao.listGoodsVo();
    }

    public GoodsVo getGoodVoByGoodsId(long goodsId) {
        return goodDao.getGoodVoByGoodsId(goodsId);
    }

    public void reduceStock(GoodsVo goods) {
        MiaoshaGoods g = new MiaoshaGoods();
        g.setGoodsId(goods.getId());
        goodDao.reduceStock(g);
    }
}
