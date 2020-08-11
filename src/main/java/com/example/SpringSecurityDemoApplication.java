package com.example;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import javax.annotation.PostConstruct;
import java.io.IOException;


@SpringBootApplication
@ServletComponentScan
@MapperScan(basePackages = "com.example.mapper")
@Slf4j
public class SpringSecurityDemoApplication {


    @PostConstruct
    public void initApplication() throws IOException {
        log.info("Running with Spring profile(s) : {}");
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityDemoApplication.class, args);
        System.out.println("swagger address:http://localhost:8074/doc.html");
        System.out.println("swagger address:http://192.168.1.11:8074/doc.html");


    }
}

