package com.calibre.forex.fetching.response;

public enum ResponseEnum {

    DATA_SUCCESS(0,"Success"), DATA_ERROR(-1,"Error");

    ResponseEnum(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
