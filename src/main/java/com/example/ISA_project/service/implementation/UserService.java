package com.example.ISA_project.service.implementation;


import com.example.ISA_project.model.User;
import com.example.ISA_project.model.dto.ProfileDTO;
import com.example.ISA_project.repository.UserRepository;
import com.example.ISA_project.service.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }



    public ProfileDTO editUser(ProfileDTO profile) {
       User user = userRepository.findOneByUsername(profile.getUsername());
        user.setAddress(profile.getAddress()); //clean code??
        user.setPhone(profile.getPhone());
        user.setState(profile.getState());
        user.setName(profile.getName());
        user.setSurname(profile.getSurname());
        user.setTown(profile.getTown());
        user.setEmail(profile.getEmail());
       userRepository.save(user);
       return profile;
    }
}
