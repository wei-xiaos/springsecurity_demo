package com.example.config;

import com.example.entity.Role;
import com.example.vo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * 登录成功处理类
 */
@Slf4j
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication) throws IOException,
            ServletException {
        //获得授权后可得到用户信息
        User userDetails = (User) authentication.getPrincipal();
        Set<Role> roles = userDetails.getRoles();
        for (Iterator s = roles.iterator(); s.hasNext(); ) {
            //打印用户角色信息
            System.out.println(((Role) s.next()).getRoleName()+",");
        }

        log.info("用户:{}登录 ", userDetails.getUsername());

       /* try {
            response.setContentType("application/json;charset=utf-8");
            PrintWriter pw = response.getWriter();
            pw.write(JSONObject.toJSONString(new Response()));
            pw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        request.getRequestDispatcher("/loginStatus").forward(request,response);
        //response.sendRedirect("/1.html");
    }

    public String getIpAddress(HttpServletRequest request){
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}