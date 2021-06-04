package com.example.ISA_project.service.implementation;

import com.example.ISA_project.model.Supplier;
import com.example.ISA_project.model.User;
import com.example.ISA_project.model.dto.ProfileDTO;
import com.example.ISA_project.repository.SupplierRepository;
import com.example.ISA_project.service.ISupplierService;
import org.springframework.stereotype.Service;

@Service
public class SupplierService implements ISupplierService {

    private final UserService userService;
    private final SupplierRepository supplierRepository;

    public SupplierService(UserService userService, SupplierRepository supplierRepository){this.userService = userService; this.supplierRepository = supplierRepository;}

    @Override
    public ProfileDTO getByEmail(String email) {
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

    @Override
    public Supplier getByEmailSupplier(String email) {
        User user = userService.findUserByEmail(email);
        Supplier supplier = (Supplier) supplierRepository.findOneByUser(user);
        return supplier;
    }


}
