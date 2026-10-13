package com.nprojects.ordermanagementapplication.controller;

import com.nprojects.ordermanagementapplication.model.LoginRequest;
import com.nprojects.ordermanagementapplication.model.RegisterRequest;
import com.nprojects.ordermanagementapplication.model.RegisterResponse;
import com.nprojects.ordermanagementapplication.model.User;
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

    public AuthController(UserService userService){
        this.userService=userService;
    }


    @PostMapping("/register")
   public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request){

        User user= userService.register(request);

        RegisterResponse response= new RegisterResponse(user.getId(),user.getEmail());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @PostMapping("/login")
    public ResponseEntity<RegisterResponse> login(@RequestBody LoginRequest request){
        User user=userService.login(request);

        RegisterResponse response= new RegisterResponse(user.getId(),user.getEmail());

        return ResponseEntity.ok(response);


    }

}
