package com.itheima.dubbo.api.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.itheima.dubbo.api.UserApi;
import com.itheima.dubbo.mapper.UserMapper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import pojo.User;

import java.util.Date;

/**
 * @program: tanhua
 * @description
 * @author: Zi'chao
 * @create: 2021-05-03 18:01
 **/
@Service
public class UserApiImpl implements UserApi {

    @Autowired
    private UserMapper userMapper;

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    @Override
    public int save(User user) {
        user.setCreated(new Date());
        user.setUpdated(new Date());
        userMapper.insert(user);
        return user.getId();
    }

    /**
     * 通过手机号码查询
     *
     * @param mobile
     * @return
     */
    @Override
    public User findByMobile(String mobile) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq("mobile", mobile);
        return userMapper.selectOne(queryWrapper);
    }
}
