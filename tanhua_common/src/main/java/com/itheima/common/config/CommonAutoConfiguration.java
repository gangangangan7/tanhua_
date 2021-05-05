package com.itheima.common.config;

import com.itheima.common.properties.SmsProperties;
import com.itheima.common.temlpates.SmsTemplate;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: tanhua
 * @description
 * @author: Zi'chao
 * @create: 2021-05-03 21:11
 **/
@Configuration
@EnableConfigurationProperties({SmsProperties.class})
public class CommonAutoConfiguration {

    @Bean
    public SmsTemplate smsTemplate(SmsProperties smsProperties) {
        SmsTemplate smsTemplate = new SmsTemplate(smsProperties);
        smsTemplate.init();
        return smsTemplate;
    }
}
