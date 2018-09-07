package com.shine.seckill.service;

import com.shine.seckill.dao.UserDao;
import com.shine.seckill.model.User;
import com.shine.seckill.result.CodeMsg;
import com.shine.seckill.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    public UserDao userDao;

    public User getById(int id){
        return userDao.getById(id);
    }

    @Transactional
    public boolean tx() {
        User user = new User(104,"钱七");
        userDao.insert(user);

        User user1 = new User(103,"孙八");
        userDao.insert(user1);
        return true;
    }

}
