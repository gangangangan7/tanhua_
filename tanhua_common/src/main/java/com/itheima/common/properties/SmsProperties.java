package com.itheima.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @program: tanhua
 * @description
 * @author: Zi'chao
 * @create: 2021-05-03 21:01
 **/
@Data
@ConfigurationProperties(prefix = "tanhua.sms")
public class SmsProperties {
    /**
     * 签名
     */
    private String signName;

    /**
     * 模板中的参数名
     */
    private String parameterName;

    /**
     * 验证码 短信模板code
     */
    private String validateCodeTemplateCode;

    private String accessKeyId;

    private String accessKeySecret;

}
