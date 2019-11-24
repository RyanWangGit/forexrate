package com.calibre.forex.fetching.response;

public class ResponseData<T> {

    public ResponseData(Integer code, String message,T data){
        this.code = code;
        this.message = message;
        this.data = data;
    }

    private Integer code;
    private String message;
    private T data;

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    @Override
    public String toString() {
        return "ResponseData{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
 }
