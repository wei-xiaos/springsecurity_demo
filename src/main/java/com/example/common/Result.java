package com.example.common;



public class Result {

    public static Response check(int count){
        if(count>0){
            return new Response();
        }
        return new Response(null,"error");
    }
}
