package com.example.service;

import com.example.common.Response;
import com.example.vo.User;

public interface IUserService {
    User findByName(String userName);

    Response registry(User user);

}
