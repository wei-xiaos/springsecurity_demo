package com.example.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Iterator;

@Service
@AllArgsConstructor
public class CustomAccessDecisionManager implements AccessDecisionManager {

    private HttpServletResponse response;

    public void decide(Authentication authentication, Object object,
                       Collection<ConfigAttribute> configAttributes)
            throws AccessDeniedException, InsufficientAuthenticationException {
        if (configAttributes == null) {
            /*try {
                response.setContentType("application/json;charset=utf-8");
                PrintWriter pw = response.getWriter();
                pw.write("无效路径");
                pw.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }*/
             return ;
        }

        Iterator<ConfigAttribute> ite = configAttributes.iterator();

        while (ite.hasNext()) {
            ConfigAttribute ca = ite.next();
            String needRole = ca.getAttribute();

            //ga 为用户所被赋予的权限。 needRole 为访问相应的资源应该具有的权限
            for (GrantedAuthority ga : authentication.getAuthorities()) {

                if (needRole.trim().equals(ga.getAuthority().trim())) {

                    return;
                }

            }

        }

        try {
            response.setContentType("application/json;charset=utf-8");
            PrintWriter pw = response.getWriter();
            pw.write("权限不足");
            pw.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return ;

    }

    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    public boolean supports(Class<?> clazz) {
        return true;
    }


}