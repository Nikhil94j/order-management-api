package com.nprojects.ordermanagementapplication.controller;

import com.nprojects.ordermanagementapplication.model.*;
import com.nprojects.ordermanagementapplication.service.JwtService;
import com.nprojects.ordermanagementapplication.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtService jwtService;

    public AuthController(UserService userService,JwtService jwtService){
        this.userService=userService;
        this.jwtService=jwtService;
    }


    @PostMapping("/register")
   public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request){

        User user= userService.register(request);

        RegisterResponse response= new RegisterResponse(user.getId(),user.getEmail());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request){

        User user=userService.login(request);

        String token= jwtService.generateToken(user.getId());

        LoginResponse response= new LoginResponse(user.getId(),user.getEmail(),token);

        return ResponseEntity.ok(response);


    }

}
