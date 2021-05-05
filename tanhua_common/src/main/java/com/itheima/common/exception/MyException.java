package com.itheima.common.exception;

import lombok.Data;

/**
 * @program: tanhua
 * @description
 * @author: Zi'chao
 * @create: 2021-05-05 14:26
 **/
@Data
public class MyException extends RuntimeException {
    private Object errorData;

    public MyException(String msg) {
        super(msg);
    }

    public MyException(Object data) {
        super();
        this.errorData = data;
    }

}
