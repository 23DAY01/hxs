package com.lypc.hxs.constant;

public interface StatusCode {

    public enum SUCCESS{
        OK(2000101,"success");

        Integer code;
        String message;

        SUCCESS(Integer code, String message) {
            this.code = code;
            this.message = message;
        }

        public Integer getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }


    public enum CLIENT{
        PARAM_MISSING(4000101,"参数缺失"),
        PARAM_TYPE_ERROR(4000102,"参数类型错误"),
        PARAM_NOT_VALID(4000103,"参数校验失败"),

        FILE_EMPTY(4000201,"文件为空"),
        FILE_TYPE_ERROR(4000202,"文件类型错误"),

        AUTH_NOLOGIN(4000301,"未登录"),
        AUTH_PERMISSION_DENIED(4000302,"没有权限"),
        AUTH_UorP_ERROR(4000303,"账号或密码错误"),

        UNKNOWN_CLIENT_ERROR(4009901,"客户端未知异常");

        Integer code;
        String message;

        CLIENT(Integer code, String message) {
            this.code = code;
            this.message = message;
        }

        public Integer getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }


    public enum SERVER{
        PARAM_OPERATION_ERROR(5000101,"参数封装失败"),

        FILE_UPLOAD_ERROR(5000201,"文件上传失败"),
        FILE_DOWNLOAD_ERROR(5000202,"文件下载失败"),


        SQL_ERROR(5000401,"数据库异常"),


        UNKNOWN_SERVER_ERROR(5009901,"服务器未知错误");

        Integer code;
        String message;

        SERVER(Integer code, String message) {
            this.code = code;
            this.message = message;
        }

        public Integer getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }

}
