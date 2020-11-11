package ink.kilig.yxy.config;

import ink.kilig.yxy.domain.Result;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *
 * @author: Hps
 * @date: 2020/11/11 21:52
 * @description:
 */
//全局异常处理 相当处理一些无法解决的异常
@RestControllerAdvice
public class GobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Object handlerException(Exception e){
        Result<String> result = new Result<>();
        result.setCode("500");
        //处理token过期的情况
        if (e instanceof ExpiredJwtException)
            result.setMessage("当前用户已经过期，请重新登录");
        else
            result.setMessage("出现了未知异常，请稍后重试或联系管理员解决");
        e.printStackTrace();
        return result;
    }
}
