package com.itheima.dubbo.api;

import pojo.User;

/**
 * @program: tanhua
 * @description
 * @author: Zi'chao
 * @create: 2021-05-03 17:56
 **/

public interface UserApi {
    /**
     * 添加用户
     * @param user
     * @return
     */
    int save(User user);

    /**
     * 通过手机号码查询
     * @param mobile
     * @return
     */
    User findByMobile(String mobile);
}
