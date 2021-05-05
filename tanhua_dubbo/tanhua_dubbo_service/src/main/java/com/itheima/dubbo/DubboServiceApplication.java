package com.itheima.dubbo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @program: tanhua
 * @description
 * @author: Zi'chao
 * @create: 2021-05-03 18:13
 **/
@MapperScan("com.itheima.dubbo.mapper")
@SpringBootApplication
public class DubboServiceApplication {
    public static void main(String[] args) {

        SpringApplication.run(DubboServiceApplication.class,args);

    }
}
