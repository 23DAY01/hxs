package com.lypc.hxs.exception;


import com.lypc.hxs.constant.StatusCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 统一异常类
 * 这个APIResponse是一个返回信息的封装类
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class BusinessException extends RuntimeException {

    protected Integer errorCode;
    protected String errorMessage;
    protected String[] errorMsgs;

    public BusinessException(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException() {
        super();
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public static BusinessException withErrorCode(Integer code,String errorMessage) {
        BusinessException businessException = new BusinessException();
        businessException.errorCode = code;
        businessException.errorMessage=errorMessage;
        return businessException;
    }

    public BusinessException withErrorMsgArguments(String... errorMsgArguments) {
        if (errorMsgArguments != null) {
            this.errorMsgs = errorMsgArguments;
        }
        return this;
    }


}
