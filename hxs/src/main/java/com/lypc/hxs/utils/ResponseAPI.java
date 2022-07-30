package com.lypc.hxs.utils;

import com.lypc.hxs.constant.StatusCode;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * rest返回对象
 * ^
 *
 * @param <T>
 */
@Data
@AllArgsConstructor
public class ResponseAPI<T> {

    /**
     * 服务器响应数据
     */
    private T data;

    /**
     * 请求是否成功
     */
    private boolean success;

    /**
     * 错误信息
     */
    private String msg;

    /**
     * 状态码
     */
    private Integer code = -1;


    public ResponseAPI(boolean success) {
        this.success = success;
    }

    public ResponseAPI(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public ResponseAPI(boolean success, T data, Integer code) {
        this.success = success;
        this.data = data;
        this.code = code;
    }

    public ResponseAPI(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public ResponseAPI(boolean success, String msg, Integer code) {
        this.success = success;
        this.msg = msg;
        this.code = code;
    }


    public static ResponseAPI<?> success() {
        return new ResponseAPI(true,StatusCode.SUCCESS.OK.getCode());
    }

    public static <T> ResponseAPI<?> success(T data) {
        return new ResponseAPI(true, data, StatusCode.SUCCESS.OK.getCode());
    }

    public static <T> ResponseAPI<?> success(Integer code) {
        return new ResponseAPI(true, null, code);
    }

    public static <T> ResponseAPI<?> success(T data, Integer code) {
        return new ResponseAPI(true, data, code);
    }

    public static ResponseAPI<?> fail() {
        return new ResponseAPI(false);
    }

    public static ResponseAPI<?> fail(String msg) {
        return new ResponseAPI(false, msg);
    }

    public static ResponseAPI<?> fail(Integer code) {
        return new ResponseAPI(false, null, code);
    }

    public static ResponseAPI<?> fail(Integer code, String msg) {
        return new ResponseAPI(false, msg, code);
    }


}