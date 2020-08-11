package com.example.config;

import com.example.service.impl.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyFilterSecurityInterceptor mySecurityFilter;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**");
        web.ignoring().antMatchers("/images/**");
        web.ignoring().antMatchers("/js/**");
        // web.ignoring().antMatchers("/templates/**");
        //web.ignoring().antMatchers("/login");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //必须添加，否则设置的不需要登录访问的post请求将不能访问
                .csrf().disable()
                .addFilterBefore(mySecurityFilter, FilterSecurityInterceptor.class)//在正确的位置添加我们自定义的过滤器
                .authorizeRequests()
                .antMatchers(
                        "/doc.html",
                        "/swagger-ui.html",
                        "/user/test",
                        "/user/registry",
                        "/swagger-resources/**",
                        "/v2/**",
                        "/webjars/**",
                        "/*.html")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .successForwardUrl("/success")
                .failureForwardUrl("/failure")
                //.successHandler(loginSuccessHandler())
                //.failureHandler(loginFailureHandler())
                .and()
                .logout()
                .logoutSuccessUrl("/login")
                .permitAll()
                .invalidateHttpSession(true)
                .and()
                .rememberMe()
                .tokenValiditySeconds(1209600);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        //指定密码加密所使用的加密器为passwordEncoder()
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
        //不删除凭据，以便记住用户
        auth.eraseCredentials(false);
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(4);
    }


    @Bean
    public LoginSuccessHandler loginSuccessHandler() {
        return new LoginSuccessHandler();
    }

    @Bean
    public LoginFailureHandler loginFailureHandler(){
        return new LoginFailureHandler();
    }
}
