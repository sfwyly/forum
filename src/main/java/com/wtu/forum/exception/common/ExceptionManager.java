package com.wtu.forum.exception.common;

import com.wtu.forum.dto.RequestResult;
import com.wtu.forum.enums.EmUserError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName ExceptionManager
 * @Description 统一异常处理
 * @Author 逝风无言
 * @Data 2019/10/8 20:38
 * @Version 1.0
 **/
@ControllerAdvice
public class ExceptionManager {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public RequestResult handler(Exception e){

        logger.error("{}",e.getMessage());//打印日志
        return new RequestResult(null,false, EmUserError.USER_INPUT_ERROR);
    }

}
