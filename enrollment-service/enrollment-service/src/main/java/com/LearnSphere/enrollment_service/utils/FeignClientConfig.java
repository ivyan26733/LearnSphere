package com.LearnSphere.enrollment_service.utils;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.ws.rs.core.HttpHeaders;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration
public class FeignClientConfig implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        String token = JwtTokenUtil.getCurrentToken();
        System.out.println("Forwarding token: " + token);
        if(token!=null && !token.isEmpty()){
            requestTemplate.header("Authorization","Bearer "+token);
        }
    }
}
