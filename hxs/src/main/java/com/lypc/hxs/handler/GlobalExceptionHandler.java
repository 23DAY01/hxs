package com.lypc.hxs.handler;

import com.lypc.hxs.constant.StatusCode;
import com.lypc.hxs.exception.BusinessException;
import com.lypc.hxs.utils.ResponseAPI;
import com.qiniu.common.QiniuException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * 统一异常处理
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理业务异常
     * @param e 异常
     * @return responseAPI
     */
    @ExceptionHandler(value = BusinessException.class)
    public ResponseAPI<?> BusinessExceptionHandler(BusinessException e){
        log.error("业务异常:{}",e.getErrorMessage());
        return ResponseAPI.fail(e.getErrorCode(),e.getErrorMessage());
    }


    /**
     * 数据库异常
     * @param sqlException
     * @return
     */
    @ExceptionHandler(value = SQLException.class)
    public ResponseAPI<?> sqlException(SQLException sqlException){
        log.error("数据库异常:{}",sqlException.getMessage());
        return ResponseAPI.fail(StatusCode.SERVER.SQL_ERROR.getCode(),StatusCode.SERVER.SQL_ERROR.getMessage());
    }

    /***
     * 参数缺失异常
     * @param missingServletRequestParameterException
     * @return
     */
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public ResponseAPI<?> missingServletRequestParameterException(MissingServletRequestParameterException missingServletRequestParameterException){
        log.error("参数缺失:{}",missingServletRequestParameterException.getMessage());
        return ResponseAPI.fail(StatusCode.CLIENT.PARAM_MISSING.getCode(),StatusCode.CLIENT.PARAM_MISSING.getMessage());
    }


    /**
     * 参数校验失败
     * @param methodArgumentNotValidException
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseAPI<?> methodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException){
        log.error("参数校验失败:{}",methodArgumentNotValidException.getMessage());
        return ResponseAPI.fail(StatusCode.CLIENT.PARAM_NOT_VALID.getCode(),StatusCode.CLIENT.PARAM_NOT_VALID.getMessage());
    }

    /**
     * 参数类型不匹配
     * @param methodArgumentTypeMismatchException
     * @return
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseAPI<?> methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException methodArgumentTypeMismatchException){
        log.error("参数类型不匹配:{}",methodArgumentTypeMismatchException.getMessage());
        return ResponseAPI.fail(StatusCode.CLIENT.PARAM_TYPE_ERROR.getCode(),StatusCode.CLIENT.PARAM_TYPE_ERROR.getMessage());
    }







}

