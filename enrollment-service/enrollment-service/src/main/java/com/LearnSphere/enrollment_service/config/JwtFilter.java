
package com.LearnSphere.enrollment_service.config;

import com.LearnSphere.enrollment_service.service.JwtService;
import com.LearnSphere.enrollment_service.utils.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private ApplicationContext context;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String jwt = authHeader.substring(7); // Remove "Bearer "

        if (jwtService.isTokenValid(jwt)) {
            String email = jwtService.extractUserName(jwt);
            String role = jwtService.extractRole(jwt);

            List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(role));

            UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(email,jwt,authorities);

            SecurityContextHolder.getContext().setAuthentication(auth);

        }
        filterChain.doFilter(request, response);
    }

    private void createErrorResponse(HttpServletResponse response, String s, int statusCode) throws IOException {
        response.setStatus(statusCode);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        ApiResponse<String> res = new ApiResponse<>(s, null, false);
        String jsonRes = new ObjectMapper().writeValueAsString(res);

        response.getWriter().write(jsonRes);
    }
}
