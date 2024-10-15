package com.event.EventManagement.controller;

import com.event.EventManagement.entity.AuthModel;
import com.event.EventManagement.entity.JwtResponse;
import com.event.EventManagement.entity.User;
import com.event.EventManagement.security.JwtTokenService;
import com.event.EventManagement.service.UserService;
import com.event.EventManagement.service.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private JwtTokenService jwtTokenService;


    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody AuthModel authModel) {

        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authModel.getEmail(), authModel.getPassword()));
        var token = jwtTokenService.generateToken(authentication);
        return new ResponseEntity<JwtResponse>(new JwtResponse(token), HttpStatus.OK);

    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User u) {
        return new ResponseEntity<>(userService.createUser(u), HttpStatus.CREATED);
    }
}
