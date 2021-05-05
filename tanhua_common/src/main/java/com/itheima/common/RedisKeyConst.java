package com.itheima.common;
/**
 * Redis Key 常量
 */
public interface RedisKeyConst {

    /** 登陆与注册时使用的验证码 */
    static final String LOGIN_VALIDATE_CODE="VALIDATE_CODE_LOGIN:";

    /** 修改手机号码时使用的验证码 */
    static final String CHANGE_MOBILE_VALIDATE_CODE="VALIDATE_CODE_CHANGE_MOBILE:";

    /** 登陆用户Token */
    static final String TOKEN = "TOKEN_";
}