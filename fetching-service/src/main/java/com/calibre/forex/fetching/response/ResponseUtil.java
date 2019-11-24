package com.calibre.forex.fetching.response;

public class ResponseUtil {

    public static <T> ResponseData<T> success(T object) {
        return new ResponseData<T>(ResponseEnum.DATA_SUCCESS.getCode(), ResponseEnum.DATA_SUCCESS.getMsg(),object);
    }

    public static <T> ResponseData<T> success(int code, T object) {
        return new ResponseData<T>(code, ResponseEnum.DATA_SUCCESS.getMsg(),object);
    }

    public static <T> ResponseData<T> success(int code,String msg,T object) {
        return new ResponseData<T>(code,msg,object);
    }

    public static <T> ResponseData<T> error(T object) {
        return new ResponseData<T>(ResponseEnum.DATA_ERROR.getCode(), ResponseEnum.DATA_ERROR.getMsg(),object);
    }

    public static <T> ResponseData<T> error(int code, T object) {
        return new ResponseData<T>(code, ResponseEnum.DATA_ERROR.getMsg(),object);
    }

    public static <T> ResponseData<T> error(int code,String msg,T object) {
        return new ResponseData<T>(code,msg,object);
    }
}
