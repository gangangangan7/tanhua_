package com.itheima;

import com.itheima.common.temlpates.SmsTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

/**
 * @program: tanhua
 * @description
 * @author: Zi'chao
 * @create: 2021-05-04 16:22
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class MsgTest {
    @Autowired
    private SmsTemplate smsTemplate;

    @Test
    public void SmsTest(){
        Map<String, String> stringStringMap = smsTemplate.sendValidateCode("17688722887", "66666");
        System.out.println(stringStringMap);

    }

}
