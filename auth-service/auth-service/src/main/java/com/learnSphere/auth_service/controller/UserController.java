package com.learnSphere.auth_service.controller;


import com.learnSphere.auth_service.dto.LoginDTO;
import com.learnSphere.auth_service.globalException.TokenGenerationException;
import com.learnSphere.auth_service.model.User;
import com.learnSphere.auth_service.service.UserService;
import com.learnSphere.auth_service.utils.ApiResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/protected/user")
public class UserController {

    @Autowired
    private UserService userService;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user){
        String password = bCryptPasswordEncoder.encode(user.getuserPassword());
        user.setuserPassword(password);
        User newuser = userService.registerUser(user);



        return new ResponseEntity<>(newuser ,HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginDTO>> validateUser(@RequestBody User user , HttpServletResponse response) throws TokenGenerationException{
        String token = userService.validateUser(user);
        User loggedInUser = userService.findByEmailId(user.getuserEmail());

        if(token.equals("FAIL")) throw new TokenGenerationException("Error Generating token");

        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUser(loggedInUser);
        loginDTO.setToken(token);

        response.setHeader("Authorization" , "Bearer" + token);
         return ResponseEntity.ok(new ApiResponse<>("Successfully Register" , loginDTO , true));
    }


}
