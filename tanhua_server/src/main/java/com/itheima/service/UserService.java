package com.itheima.service;

import pojo.User;

import java.util.Map;

/**
 * @program: tanhua
 * @description
 * @author: Zi'chao
 * @create: 2021-05-03 19:59
 **/
public interface UserService {


    User findUser(String phone);

    int saveUser(User user);

    Map<String, Object> loginVerification(Map < String, String > param);

    void sendValidateCode(String phone);
}
