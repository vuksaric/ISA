package com.example.ISA_project.controller;

import com.example.ISA_project.model.dto.ProfileDTO;
import com.example.ISA_project.service.IUserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final IUserService userService;

    public UserController(IUserService userService){
        this.userService=userService;
    }
    @PostMapping(consumes = "application/json")
    public ProfileDTO editUser(@RequestBody ProfileDTO profile){
        return userService.editUser(profile);
    }
}
