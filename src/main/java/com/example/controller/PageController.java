package com.example.controller;

import com.example.common.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PageController {

    @RequestMapping("/success")
    public Response success(){
        return new Response();
    }

    @RequestMapping("/failure")
    public Response failure(){
        return new Response(500,"登录失败");
    }

}
