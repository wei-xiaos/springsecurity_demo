package com.example.common;

import lombok.Data;

@Data
public class Response<T> {
    private Integer code;
    private T data;
    private String msg;

    public Response(){
        this.code=200;
        this.data=null;
        this.msg="success";
    }

    public Response(Integer code, T data, String msg){
        this.code=code;
        this.data=data;
        this.msg=msg;
    }

    public Response(Integer code, String msg){
        this.code=code;
        this.msg=msg;
        this.data=null;
    }

    public Response(T data){
        this.code=200;
        this.data=data;
        this.msg="success";
    }

    public Response(T data, String msg){
        this.code=500;
        this.data=data;
        this.msg=msg;
    }
}
