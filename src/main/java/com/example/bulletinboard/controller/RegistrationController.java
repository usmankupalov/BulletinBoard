package com.example.bulletinboard.controller;

import com.example.bulletinboard.controller.auth.AuthRequest;
import com.example.bulletinboard.controller.auth.AuthResponse;
import com.example.bulletinboard.controller.response.Response;
import com.example.bulletinboard.filter.JwtProvider;
import com.example.bulletinboard.model.User;
import com.example.bulletinboard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1.0")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/registration")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        User user1 = userService.findUserByLogin(user.getLogin());
        if (user1 != null) {
            return new ResponseEntity<>(new Response("User with this login exists!!!"), HttpStatus.BAD_REQUEST);
        }
        userService.registerUser(user);
        return new ResponseEntity<>(new Response(user.getLogin() + " registered"), HttpStatus.OK);
    }

    @PostMapping("/auth")
    public AuthResponse authUser(@RequestBody AuthRequest authRequest) {
        try {
            User user = userService.findUserByLoginAndPassword(authRequest.getLogin(), authRequest.getPassword());
            String token = jwtProvider.generateToken(user.getLogin());
            return new AuthResponse(token);
        } catch (NullPointerException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }
}
