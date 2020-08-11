package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.*;

import java.nio.charset.Charset;
import java.util.List;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

   @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/console").setViewName("console");
        registry.addViewController("/hello").setViewName("hello");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/test").setViewName("test");
      // registry.addViewController("/").setViewName("console");
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/*.html").addResourceLocations("file:D:\\test_demo\\");
        //registry.addResourceHandler("/*.html").addResourceLocations("file:F:\\1-work\\项目\\industry-project\\src\\views\\");
         //registry.addResourceHandler("/*.html").addResourceLocations("classpath:templates/");
        //registry.addResourceHandler("/").addResourceLocations("file:F:\\1-work\\项目\\industry-project\\src\\views\n");

        //registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:");
        /*registry.addResourceHandler("/*.html").addResourceLocations("file:D:/test_demo/");*/
       // registry.addResourceHandler("/*.html").addResourceLocations("file:F:\\1-work\\项目\\industry-project\\src\\views\n");
    }

    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        StringHttpMessageConverter converter = new StringHttpMessageConverter(
                Charset.forName("UTF-8"));
        return converter;
    }

    @Override
    public void configureMessageConverters(
            List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        converters.add(responseBodyConverter());
    }

    @Override
    public void configureContentNegotiation(
            ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false);
    }

}