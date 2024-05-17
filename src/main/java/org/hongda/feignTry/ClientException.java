package org.hongda.feignTry;

/**
 * @ClassName ClientException
 * @Description TODO
 * @Author liuyibo
 * @Date 2024/5/17 14:30
 **/
public class ClientException  extends RuntimeException{
    public ClientException() {
    }

    public ClientException(String message) {
        super(message);
    }

    public ClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClientException(Throwable cause) {
        super(cause);
    }

    public ClientException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
