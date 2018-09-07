package com.shine.seckill.redis;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


@Service
public class RedisService {

    @Autowired
    JedisPool jedisPool;

    //region 获取单个对象
    public <T> T get(KeyPrefix prefix, String key, Class<T> clazz) {
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            //生成真正的Key
            String realKey=prefix.getPrefix()+key;
            String str = jedis.get(realKey);
            T t=stringToBean(str,clazz);
            return t;
        }finally {
                    // 正确释放资源
            if(jedis != null ) {
                jedisPool.returnBrokenResource(jedis);//获取连接失败时，应该返回给pool,否则每次发生异常将导致一个jedis对象没有被回收。
            }
        }
    }
    //endregion

    //region 设置对象
    public <T> boolean set(KeyPrefix prefix, String key, T value) {
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            String str=beanToString(value);
            if (str==null||str.length()<=0) {
                return false;
            }
            //生成真正的Key
            String realKey=prefix.getPrefix()+key;
            int seconds = prefix.expireSeconds();
            if (seconds<=0) {
                jedis.set(realKey, str);
            }else {
                jedis.setex(realKey, seconds, str);
            }
            jedis.set(realKey, str);
            return true;
        }finally {
            // 正确释放资源
            if(jedis != null ) {
                jedisPool.returnBrokenResource(jedis);//获取连接失败时，应该返回给pool,否则每次发生异常将导致一个jedis对象没有被回收。
            }
        }
    }
    //endregion

    //region 判断key是否存在
    public <T> boolean exists(KeyPrefix prefix, String key) {
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            //生成真正的Key
            String realKey=prefix.getPrefix()+key;
            return jedis.exists(realKey);
        }finally {
            // 正确释放资源
            if(jedis != null ) {
                jedisPool.returnResource(jedis);//获取连接失败时，应该返回给pool,否则每次发生异常将导致一个jedis对象没有被回收。
            }
        }
    }
    //endregion

    //region 增加值
    public <T> Long incr(KeyPrefix prefix, String key) {
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            //生成真正的Key
            String realKey=prefix.getPrefix()+key;
            return jedis.incr(realKey);
        }finally {
            // 正确释放资源
            if(jedis != null ) {
                jedisPool.returnResource(jedis);//获取连接失败时，应该返回给pool,否则每次发生异常将导致一个jedis对象没有被回收。
            }
        }
    }
    //endregion

    //region 减少值
    public <T> Long decr(KeyPrefix prefix, String key) {
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            //生成真正的Key
            String realKey=prefix.getPrefix()+key;
            return jedis.decr(realKey);
        }finally {
            // 正确释放资源
            if(jedis != null ) {
                jedisPool.returnResource(jedis);//获取连接失败时，应该返回给pool,否则每次发生异常将导致一个jedis对象没有被回收。
            }
        }
    }
    //endregion

    private <T> String beanToString(T value) {
        if (value==null) {
            return null;
        }
        Class<?> clazz = value.getClass();
        if (clazz==int.class||clazz==Integer.class) {
            return "" + value;
        }else if (clazz==String.class) {
            return (String)value;
        }else if (clazz==long.class||clazz==Long.class) {
            return "" + value;
        }else {
            return JSON.toJSONString(value);
        }
    }

    private <T> T stringToBean(String str,Class<T> clazz) {
        if (str==null||str.length()<=0||clazz==null) {
            return null;
        }
        if (clazz==int.class||clazz==Integer.class) {
            return (T)Integer.valueOf(str);
        }else if (clazz==String.class) {
            return (T) str;
        }else if (clazz==long.class||clazz==Long.class) {
            return (T) Long.valueOf(str);
        }else {
            return JSON.toJavaObject(JSON.parseObject(str),clazz);
        }
    }

    private void returnToPool(Jedis jedis) {
        if (jedis!=null) {
            jedis.close();
        }
    }

}
