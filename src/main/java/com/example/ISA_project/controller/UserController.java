package com.example.ISA_project.controller;

import com.example.ISA_project.model.dto.PasswordDTO;
import com.example.ISA_project.model.dto.ProfileDTO;
import com.example.ISA_project.service.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final IUserService userService;

    public UserController(IUserService userService){
        this.userService=userService;
    }

    @PutMapping (consumes = "application/json")
    public ResponseEntity<ProfileDTO> editUser(@RequestBody ProfileDTO profile){
        return new ResponseEntity<>(userService.editUser(profile), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity changePassword(@RequestBody PasswordDTO passwordDTO) {
        try {

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity editProfile(@RequestBody ProfileDTO profileDTO) {
        try {
            return new ResponseEntity(userService.editUser(profileDTO),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
