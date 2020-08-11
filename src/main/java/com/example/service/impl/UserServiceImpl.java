package com.example.service.impl;

import com.example.common.Response;
import com.example.common.enums.StatusCode;
import com.example.common.util.AccountUtil;
import com.example.mapper.UserMapper;
import com.example.service.IUserService;
import com.example.vo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByName(String userName) {
        return null;
    }

    @Override
    public Response registry(User user) {
        String pwd=user.getPassword();
        int count=userMapper.exists(user);
        if(count>0){
            return new Response(StatusCode.EXISTS.getCode(),StatusCode.EXISTS.getMsg());
        }
        user.setPassword(AccountUtil.getPassword(user.getPassword()));
        int result=userMapper.addUser(user);
        user.setPassword(pwd);
        if(result>0){
            return new Response(user);
        }
        return new Response(StatusCode.ERROR_CODE.getCode(),StatusCode.ERROR_CODE.getMsg());

    }
}
