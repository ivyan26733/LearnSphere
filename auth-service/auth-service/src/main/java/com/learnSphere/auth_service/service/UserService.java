package com.learnSphere.auth_service.service;


import com.learnSphere.auth_service.dto.LoginDTO;
import com.learnSphere.auth_service.model.User;
import com.learnSphere.auth_service.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;


    public User registerUser(User user) {
        return userRepo.save(user);
    }

    public String validateUser(User user){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getuserEmail(),user.getuserPassword()));

        if(authentication.isAuthenticated()){
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return this.jwtService.generateToken(user.getuserEmail(),userDetails);
        }

        return "FAIL";
    }

    public User findByEmailId(String s) {
        return userRepo.findByUserEmail(s);
    }
}
