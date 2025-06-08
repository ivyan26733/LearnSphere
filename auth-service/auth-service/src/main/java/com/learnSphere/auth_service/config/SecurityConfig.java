package com.learnSphere.auth_service.config;


import com.learnSphere.auth_service.service.UserDetailsSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@EnableWebSecurity
@Configuration
public class SecurityConfig {


    @Autowired
    private UserDetailsSer userDetailsService;
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
                        auth.requestMatchers("/auth/user/register", "/auth/user/login")
                                .permitAll()
//                                .requestMatchers(new String[]{"/protected/products/admin/**"}).hasAuthority("ROLE_ADMIN").requestMatchers(new String[]{"/protected/user/customer/**", "/protected/carts/**"})
//                                .hasAuthority("ROLE_CUSTOMER")
                                .anyRequest()).authenticated())
                .httpBasic(Customizer.withDefaults()).sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }


    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        provider.setUserDetailsService(this.userDetailsService);
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }


}
