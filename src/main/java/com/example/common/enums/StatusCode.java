package com.example.common.enums;

import lombok.Data;


public enum  StatusCode {
    SUCCESS_CODE(200,"success"),
    ERROR_CODE(500,"fail"),
    EXISTS(1111,"已存在该资源");


    private Integer code;
    private String msg;

    StatusCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
