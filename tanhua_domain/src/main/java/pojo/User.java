package pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: tanhua
 * @description
 * @author: Zi'chao
 * @create: 2021-05-03 17:48
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private Integer id;
    //密码，json序列化时忽略
    @JSONField(serialize = false)
    private String password;
    //手机号
    private String mobile;
    private Date updated;
    private Date created;

}
