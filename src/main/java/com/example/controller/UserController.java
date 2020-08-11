package com.example.controller;

import com.example.common.Response;
import com.example.service.IUserService;
import com.example.vo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private IUserService userService;

    @RequestMapping("/test")
    @ResponseBody
    public Response test(){
        return new Response("test........");
    }

    @RequestMapping("/")
    @ResponseBody
    public Response tests(ModelAndView modelAndView ){
        modelAndView.addObject("error","errorrrrrr");
        modelAndView.addObject("error","errorrrrrr");
        return
                new Response("test........");
    }

    @PostMapping("/registry")
    @ResponseBody
    public Response registry(@RequestBody  User user){
        log.info("username:"+user.getUsername());
        log.info("password:"+user.getPassword());
        if(user.getUsername()==null||user.getPassword()==null){
            return new Response(1234,"账号或密码不能为null");
        }
        return userService.registry(user);
    }


}
