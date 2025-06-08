package com.LearnSphere.enrollment_service.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        System.out.println("SecurityFilterChain is being initialized .... ");
        return (SecurityFilterChain)http
                .csrf((csrf) -> csrf.disable())
//                .exceptionHandling(e -> e.accessDeniedHandler(customAccessDeniedHandler))
                .headers((headers) -> headers.frameOptions((frameOptions) -> frameOptions.sameOrigin()))
                .authorizeHttpRequests((auth) -> ((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)
                        auth
                                .requestMatchers(new String[]{"/protected/instructor/**"}).hasAuthority("ROLE_INSTRUCTOR").requestMatchers(new String[]{"/protected/student/**"})
                                .hasAuthority("ROLE_STUDENT")
                                .anyRequest()).authenticated())
                .httpBasic(Customizer.withDefaults()).sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }


}
