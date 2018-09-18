package com.shine.seckill.dao;

import com.shine.seckill.model.MiaoshaOrder;
import com.shine.seckill.model.OrderInfo;
import org.apache.ibatis.annotations.*;

/**
 * Create By shine
 * 2018-09-18 21:26
 */
@Mapper
public interface OrderDao {

    @Select("select * from miaosha_order where user_id =#{userId} and goods_id=#{goodsId}")
    MiaoshaOrder getMaioshaOrderByUserIdGoodsId(@Param("userId") long userId, @Param("goodsId") long goodsId);

    @Insert("insert into order_info(user_id, goods_id, goods_name, goods_count, goods_price, order_channel, status, create_date)values("
            + "#{userId}, #{goodsId}, #{goodsName}, #{goodsCount}, #{goodsPrice}, #{orderChannel},#{status},#{createDate} )")
    @SelectKey(keyColumn="id", keyProperty="id", resultType=long.class, before=false, statement="select last_insert_id()")
    long insert(OrderInfo orderInfo);

    @Insert("insert into miaosha_order (user_id, goods_id, order_id)values(#{userId}, #{goodsId}, #{orderId})")
    int insertMiaoshaOrder(MiaoshaOrder miaoshaOrder);
}
