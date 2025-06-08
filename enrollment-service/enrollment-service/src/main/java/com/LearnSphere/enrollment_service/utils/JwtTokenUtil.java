package com.LearnSphere.enrollment_service.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenUtil {
    public static String getCurrentToken(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication!=null && authentication.getCredentials() instanceof String){
            return (String) authentication.getCredentials();
        }
        return null;
    }
}
