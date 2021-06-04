package com.example.ISA_project.service.implementation;

import com.example.ISA_project.model.User;
import com.example.ISA_project.model.dto.ProfileDTO;
import com.example.ISA_project.service.ISupplierService;
import org.springframework.stereotype.Service;

@Service
public class SupplierService implements ISupplierService {

    private final UserService userService;

    public SupplierService(UserService userService){this.userService = userService;}

    @Override
    public ProfileDTO getSupplierByEmail(String email) {
        User user = userService.findUserByEmail(email);
        ProfileDTO profile = new ProfileDTO();
        profile.setName(user.getName());
        profile.setSurname(user.getSurname());
        profile.setEmail(user.getEmail());
        profile.setAddress(user.getAddress().getStreet());
        profile.setState(user.getAddress().getState());
        profile.setTown(user.getAddress().getTown());
        profile.setPhone(user.getPhone());
        return profile;


    }
}
