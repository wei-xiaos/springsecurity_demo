package com.example.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.UUID;

@Slf4j
public class AccountUtil {

    public static String getAccount(){
        String account= UUID.randomUUID().toString().substring(0,4);
        return account;
    }

    public static String getPassword(String pwd){
        String encodePwd=new BCryptPasswordEncoder().encode(pwd);
        log.info("加密密码"+encodePwd);
        return encodePwd;
    }
}
