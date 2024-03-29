package org.hongda.vo;

import lombok.Data;

/**
 * @ClassName Result
 * @Description TODO
 * @Author liuyibo
 * @Date 2024/3/18 9:43
 **/
@Data
public class Result<T> implements  java.io.Serializable{
    private boolean success = false;
    
    private String msg;

    private Integer errorCode=0;

    private T value;

    public Result() {
        super();
    }

    public Result(boolean success, String msg, Integer errorCode) {
        super();
        this.success = success;
        this.msg = msg;
        this.errorCode = errorCode;
    }

    public static <T extends Object> Result<T> success(){
        return new Result<>(true,"成功！",200);


    }

    public static <T extends Object> Result<T> success(T value){
        Result<T> result = new Result<T>();
        result.setSuccess(true);
        result.setValue(value);
        return result;
    }

    public static <T extends Object> Result<T> failure(String msg,Integer errorCode){
        Result<T> result = new Result<T>();
        result.setSuccess(false);
        result.setMsg(msg);
        result.setErrorCode(errorCode);
        return result;
    }


}
