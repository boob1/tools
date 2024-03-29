package org.hongda.checkParams.config;

import org.hongda.vo.Result;
import org.springframework.beans.NotReadablePropertyException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

/**
 * @ClassName CommonExceptionHandler
 * @Description TODO
 * @Author liuyibo
 * @Date 2024/3/22 17:36
 **/
@RestControllerAdvice
public class CommonExceptionHandler {



    @ResponseBody
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.OK)
    public Result handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        BindingResult bindingResult = ex.getBindingResult();
        StringBuffer sb = new StringBuffer("校验失败：");
        for(FieldError fieldError :bindingResult.getFieldErrors()){
            sb.append(fieldError.getField()).append(fieldError.getDefaultMessage()).append(",");
        }
        String msg = sb.toString();
        return  Result.failure(msg,500);
    }
    @ResponseBody
    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.OK)
    public Result handleConstraintViolationException(ConstraintViolationException ex){
        return  Result.failure(ex.getMessage(),500);
    }

    @ResponseBody
    @ExceptionHandler({NotReadablePropertyException.class})
    @ResponseStatus(HttpStatus.OK)
    public Result handleNotReadablePropertyException(NotReadablePropertyException ex){
        return  Result.failure(ex.getPropertyName(),500);
    }

    @ResponseBody
    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.OK)
    public Result commonException(Exception ex){
        return  Result.failure(ex.getMessage(),501);
    }
}