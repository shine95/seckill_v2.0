package com.shine.seckill.dao;

import com.shine.seckill.model.MiaoshaUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface MiaoshaUserDao {

    @Select("select * from miaosha_user where id=#{id}")
    MiaoshaUser getById(@Param("id") long id);

    @Update("update miaosha_user set login_count=login_count+1 where id=#{id}")
    int setLoginCount(@Param("id")long id);

}
