package com.example.ISA_project.service;

import com.example.ISA_project.model.User;
import com.example.ISA_project.model.dto.ProfileDTO;

public interface IUserService {
    ProfileDTO editUser(ProfileDTO profile);
    ProfileDTO getProfile(int id);
    ProfileDTO getProfile(String username);
    User findUserByEmail(String email);
}
