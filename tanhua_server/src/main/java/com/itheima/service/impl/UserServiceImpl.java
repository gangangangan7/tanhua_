package com.itheima.service.impl;

import ch.qos.logback.core.util.TimeUtil;
import com.alibaba.fastjson.JSON;
import com.itheima.common.exception.MyException;
import com.itheima.common.temlpates.SmsTemplate;
import com.itheima.dubbo.api.UserApi;
import com.itheima.service.UserService;
import com.itheima.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.TimeoutUtils;
import org.springframework.stereotype.Service;
import pojo.User;
import vo.ErrorResult;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @program: tanhua
 * @description
 * @author: Zi'chao
 * @create: 2021-05-03 20:06
 **/
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    boolean isNew ;

    @Reference
    private UserApi userApi;

    @Value("${tanhua.redisValidateCodeKeyPrefix}")
    private String redisValidateCodeKeyPrefix;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private SmsTemplate smsTemplate;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public User findUser(String phone) {
        return userApi.findByMobile(phone);
    }

    @Override
    public int saveUser(User user) {

        return userApi.save(user);
    }

    @Override
    public Map<String, Object> loginVerification(Map<String, String> param) {
        /*
        实现思路:获得前端以json形式传递进来的数据,校验验证码,如果redis中存在该验证码,
        则登录成功,如用户不存在则将手机号存进数据库,并调用远程Api登录.
         */
        String phone = param.get("phone");
        String verificationCode = param.get("verificationCode");
        String key = redisValidateCodeKeyPrefix + phone;
        String codeInRedis = (String) redisTemplate.opsForValue().get(key);
        log.info("verificationCode={},codeInRedis={},phone={}",verificationCode, codeInRedis, phone);
        if (StringUtils.isEmpty(codeInRedis)) {
            //验证码不存在
            throw new MyException(ErrorResult.loginError());
        }
        if (!StringUtils.equals(codeInRedis, verificationCode)) {
            //验证码与Redis中的不一致,报错
            throw new MyException(ErrorResult.validateCodeError());
        }
        redisTemplate.delete(key);
        //验证码存在,验证手机号与验证码是否一致,调用远程Api
        User newUser = userApi.findByMobile(phone);
//        boolean isNew ;
        if (newUser==null){
            //验证码存在,手机号不存在,将手机号存进数据库,调用远程Api
            isNew =true;
            newUser = new User();
            //新建用户
            newUser.setMobile(phone);
            //设置密码 默认为手机号后六位
            newUser.setPassword(phone.substring(6));
            int userId = userApi.save(newUser);
            newUser.setId(userId);
        }
        String userSting = JSON.toJSONString(newUser);
        String token = jwtUtil.createJWT(phone, newUser.getId());
        redisTemplate.opsForValue().set("TOKEN_"+token,userSting,7,TimeUnit.DAYS);
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("token",token);
        resultMap.put("isNew",isNew);
         return resultMap;
    }

    @Override
    public void sendValidateCode(String phone) {
        log.info("收到验证码的号码{}", phone);
        /*
        查询Redis中是否存在验证码,如果存在,擦除验证码,重新发送验证码
        如果不存在,发送验证码到指定手机
         */
        String key = redisValidateCodeKeyPrefix + phone;
        String codeInRedis = (String) redisTemplate.opsForValue().get(key);
        log.info("codeInRedis={},{}", codeInRedis, phone);
        if (StringUtils.isNotEmpty(codeInRedis)) {
            // 存在,删除旧验证码,发新验证码
            redisTemplate.delete(key);
            String validateCode = RandomStringUtils.randomNumeric(6);
            smsTemplate.sendValidateCode(phone, validateCode);
            //把数据存进Redis.并且设置时长和时长的单位
            redisTemplate.opsForValue().set(key, validateCode, 10, TimeUnit.MINUTES);

        }
        if (StringUtils.isEmpty(codeInRedis)) {
            // 不存在 发新验证码
            String validateCode = RandomStringUtils.randomNumeric(6);
            smsTemplate.sendValidateCode(phone, validateCode);
            //把数据存进Redis.并且设置时长和时长的单位
            redisTemplate.opsForValue().set(key, validateCode, 10, TimeUnit.MINUTES);


        }
    }


}
