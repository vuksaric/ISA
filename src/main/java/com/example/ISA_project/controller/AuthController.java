package com.example.ISA_project.controller;

import com.example.ISA_project.model.User;
import com.example.ISA_project.model.dto.LoginRequest;
import com.example.ISA_project.model.dto.RegistrationRequest;
import com.example.ISA_project.model.dto.UserResponse;
import com.example.ISA_project.service.IAuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final IAuthService authService;

    public AuthController(IAuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public UserResponse login(@RequestBody LoginRequest request){
        return authService.login(request);
    }

    @PostMapping("/registration")
    public UserResponse registerSubject(@RequestBody User request){
        return authService.registerSubject(request);
    }
}
