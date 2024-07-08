package com.BEFresherTP.controller;

import com.BEFresherTP.DTO.LoginDTO;
import com.BEFresherTP.DTO.RegisterDTO;
import com.BEFresherTP.service.serviceImp.UserServiceImp;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthenController {

    private final UserServiceImp userService;

    public AuthenController(UserServiceImp userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDTO loginDTO) {
        return userService.login(loginDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterDTO registerDTO) {
        return userService.registerUser(registerDTO);
    }
}
