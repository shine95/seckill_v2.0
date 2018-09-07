package com.shine.seckill.service;

import com.shine.seckill.dao.MiaoshaUserDao;
import com.shine.seckill.exception.GlobalException;
import com.shine.seckill.model.MiaoshaUser;
import com.shine.seckill.result.CodeMsg;
import com.shine.seckill.util.MD5Util;
import com.shine.seckill.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MiaoshaUserService {

    @Autowired
    MiaoshaUserDao miaoshaUserDao;

    MiaoshaUser getById(long id){
        return miaoshaUserDao.getById(id);
    }

    public boolean login(LoginVo loginVo) {
        if (loginVo==null) {
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }
        //用户输入的手机号
        String mobile = loginVo.getMobile();
        //用户输入的密码
        String inputPass = loginVo.getPassword();
        //判断手机号是否存在
        MiaoshaUser user = getById(Long.parseLong(mobile));
        if (user==null) {
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }
        //验证密码
        String dbPasswrod = user.getPassword();
        String saltDB = user.getSalt();
        String finalPass = MD5Util.formPassDBPass(inputPass,saltDB);
        if (!finalPass.equals(dbPasswrod)) {
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }
        return true;
    }

}
