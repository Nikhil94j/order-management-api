package com.nprojects.ordermanagementapplication.service;

import com.nprojects.ordermanagementapplication.exception.EmailAlreadyRegisteredException;
import com.nprojects.ordermanagementapplication.model.LoginRequest;
import com.nprojects.ordermanagementapplication.model.RegisterRequest;
import com.nprojects.ordermanagementapplication.model.User;
import com.nprojects.ordermanagementapplication.repository.UserRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
    }

    public User register(RegisterRequest request){

        if(userRepository.existsByEmail(request.getEmail())){

            throw new EmailAlreadyRegisteredException("Email is already registered");
        }

        String encodedPassword=passwordEncoder.encode(request.getPassword());

        User user=new User(request.getEmail(),encodedPassword);

       return userRepository.save(user);
    }

    public User login(LoginRequest request){

        User user=userRepository.findByEmail(request.getEmail()).orElseThrow(
                ()-> new BadCredentialsException("Invalid email or password"));

       if(!passwordEncoder.matches(request.getPassword(),user.getPassword())) {
           throw new BadCredentialsException("Invalid email or password");
       }

       return user;
    }
}
