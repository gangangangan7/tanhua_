package com.itheima.advice;

import com.itheima.common.exception.MyException;
import vo.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @program: tanhua
 * @description
 * @author: Zi'chao
 * @create: 2021-05-05 14:31
 **/
@Slf4j
public class ExceptionAdvice {

    /**
     * 处理自定义的业务异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(MyException.class)
    public ResponseEntity handleTanHuaException(MyException ex) {
        if (null != ex.getErrorData()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getErrorData());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                ErrorResult.error("000009", ex.getMessage())
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception ex) {
        log.error("发生未知异常", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorResult.error());
    }
}