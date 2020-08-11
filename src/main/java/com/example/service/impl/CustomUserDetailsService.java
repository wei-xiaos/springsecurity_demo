package com.example.service.impl;

import com.example.mapper.UserMapper;
import com.example.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserMapper userService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userService.findByName(userName);
        user.setRoles(userService.queryRoleByUserId(user.getId()));
        if (user == null) {
            throw new UsernameNotFoundException("UserName " + userName + " not found");
        }
        return user;
    }
}
