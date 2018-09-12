package com.shine.seckill.service;

import com.shine.seckill.dao.MiaoshaUserDao;
import com.shine.seckill.exception.GlobalException;
import com.shine.seckill.model.MiaoshaUser;
import com.shine.seckill.redis.MiaoshaUserKey;
import com.shine.seckill.redis.RedisService;
import com.shine.seckill.result.CodeMsg;
import com.shine.seckill.util.MD5Util;
import com.shine.seckill.util.UUIDUtil;
import com.shine.seckill.vo.LoginVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;


@Service
public class MiaoshaUserService {

    public static final String COOKIE_NAME_TOKEN = "token";

    @Autowired
    MiaoshaUserDao miaoshaUserDao;
    @Autowired
    RedisService redisService;

    MiaoshaUser getById(long id) {
        return miaoshaUserDao.getById(id);
    }

    public int setLoginCount(long id){
        return miaoshaUserDao.setLoginCount(id);
    }

    public boolean login(HttpServletResponse response, LoginVo loginVo) {
        if (loginVo == null) {
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }
        //用户输入的手机号
        String mobile = loginVo.getMobile();
        //用户输入的密码
        String inputPass = loginVo.getPassword();
        //判断手机号是否存在
        MiaoshaUser user = getById(Long.parseLong(mobile));
        if (user == null) {
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }
        //验证密码
        String dbPasswrod = user.getPassword();
        String saltDB = user.getSalt();
        String finalPass = MD5Util.formPassDBPass(inputPass, saltDB);
        if (!finalPass.equals(dbPasswrod)) {
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }
        //生成cookie
        addCookie(response, user);
        return true;
    }

    public MiaoshaUser getByToken(HttpServletResponse response, String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        MiaoshaUser user = redisService.get(MiaoshaUserKey.token, token, MiaoshaUser.class);
        //延长有效期
        if (user != null) {
            addCookie(response, user);
        }
        return user;
    }

    private void addCookie(HttpServletResponse response, MiaoshaUser user) {
        //生成cookie
        String token = UUIDUtil.uuid();
        redisService.set(MiaoshaUserKey.token, token, user);
        Cookie cookie = new Cookie(COOKIE_NAME_TOKEN, token);
        cookie.setMaxAge(MiaoshaUserKey.token.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
    }

}
