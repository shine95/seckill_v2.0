package com.shine.seckill.dao;

import com.shine.seckill.vo.GoodsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GoodDao {

    @Select("select g.*,mg.stock_count,mg.start_date,mg.end_date from miaosha_goods mg left join goods g on mg.goods_id=g.id")
    public List<GoodsVo> listGoodsVo();

}
