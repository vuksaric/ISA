package com.example.ISA_project.service.implementation;


import com.example.ISA_project.model.Address;
import com.example.ISA_project.model.Pharmacist;
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
       Address address= user.getAddress(); //clean code??
        address.setStreet(profile.getAddress());
        address.setState(profile.getState());
        address.setTown(profile.getTown());
        user.setPhone(profile.getPhone());
        user.setName(profile.getName());
        user.setSurname(profile.getSurname());
       userRepository.save(user);
       return profile;
    }

    @Override
    public ProfileDTO getProfile(int id) {
        User user = userRepository.findOneById(id);
        ProfileDTO profile = new ProfileDTO(user.getUsername(),user.getName(), user.getSurname(),
                user.getEmail(), user.getAddress().getStreet(), user.getAddress().getState(),
                user.getAddress().getTown(), user.getPhone());
        return profile;
    }
}
