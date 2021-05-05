package com.itheima;

import com.sun.glass.ui.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;

/**
 * @program: tanhua
 * @description
 * @author: Zi'chao
 * @create: 2021-05-04 16:16
 **/
@SpringBootApplication
public class ServerApplication {
    //消费者启动类
    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class,args);
    }

    /**
     * redisTemplate默认的序列化是jdk 将其改为字符串
     * @param redisTemplate
     */
    @Resource
    public void setKeySerializer(RedisTemplate redisTemplate){
        redisTemplate.setKeySerializer(new StringRedisSerializer());
    }
}
