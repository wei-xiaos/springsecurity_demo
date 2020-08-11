package com.example.service.impl;

import com.example.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class CustomInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {


    @Autowired
   private UserMapper userMapper;

    private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

   @PostConstruct
    private void loadResourceDefine() {
        resourceMap = new HashMap();
        List<Map<String, Object>> list = userMapper.findAll();
        if (list != null && list.size() > 0) {
            for (Map<String, Object> sr : list) {
                String name  = sr.get("role_name").toString();
                Integer roleId=Integer.parseInt(sr.get("role_id").toString());
                ConfigAttribute ca = new SecurityConfig(name);
                List<Map<String, Object>> list1 = userMapper.findByRoleName(roleId.toString());
                if (list1 != null && list1.size() > 0) {
                    for (Map<String, Object> map : list1) {
                        String path = map.get("path").toString();
                        String url = path;
                        if (resourceMap.containsKey(url)) {
                            Collection<ConfigAttribute> value = resourceMap.get(url);
                            value.add(ca);
                            resourceMap.put(url, value);
                        } else {
                            Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
                            atts.add(ca);
                            resourceMap.put(url, atts);
                        }
                    }
                }
            }
        }

       for(Map.Entry<String, Collection<ConfigAttribute>> s: resourceMap.entrySet()){
           System.out.print(s.getKey()+s.getValue()+",");
       }


    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {

       return new ArrayList<ConfigAttribute>();
    }

    // 根据URL，找到相关的权限配置。</span>
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object)
            throws IllegalArgumentException {
        System.out.println("验证url");
        // object 是一个URL，被用户请求的url。</span>
        FilterInvocation filterInvocation = (FilterInvocation) object;
        if (resourceMap == null) {
            loadResourceDefine();
        }
        Iterator<String> ite = resourceMap.keySet().iterator();
        while (ite.hasNext()) {
            String resURL = ite.next();
            System.out.println("访问路径"+resURL);
            RequestMatcher requestMatcher = new AntPathRequestMatcher(resURL);
            if (requestMatcher.matches(filterInvocation.getHttpRequest())) {
                return resourceMap.get(resURL);
            }
        }

        return null;
    }

    @Override
    public boolean supports(Class<?> arg0) {

        return true;
    }

}
