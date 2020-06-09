package cn.sh.softline.exception;

import cn.sh.softline.common.entity.Result;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
    //此处应该有日志

    @ExceptionHandler(value = CommonException.class)
    public Result commonExceptionHandler(HttpServletRequest request,CommonException ce){
        //此处应该有日志
        return new Result(ce.getCode());
    }
    @ExceptionHandler(value = Exception.class)
    public Result exceptionHandler(HttpServletRequest request ,Exception e){
        //此处应该有日志
        return Result.ERROR();
    }
}
