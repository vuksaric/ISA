package com.example.ISA_project.service;

import com.example.ISA_project.model.User;
import com.example.ISA_project.model.dto.LoginRequest;
import com.example.ISA_project.model.dto.RegistrationRequest;
import com.example.ISA_project.model.dto.UserResponse;

public interface IAuthService {
    UserResponse login(LoginRequest request);
    UserResponse registerSubject(User request);
}
