package com.example.ISA_project.service.implementation;


import com.example.ISA_project.model.Address;
import com.example.ISA_project.model.User;
import com.example.ISA_project.model.dto.PasswordDTO;
import com.example.ISA_project.model.dto.ProfileDTO;
import com.example.ISA_project.repository.UserRepository;
import com.example.ISA_project.service.IUserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ProfileDTO editUser(ProfileDTO profile) {
       User user = userRepository.findOneByEmail(profile.getEmail());
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
        return new ProfileDTO(user.getName(), user.getSurname(),
                user.getEmail(), user.getAddress().getStreet(), user.getAddress().getState(),
                user.getAddress().getTown(), user.getPhone(), "GOLD",user.getDateOfBirth());

    }

    public User findUserByEmail(String email){
        return userRepository.findOneByEmail(email);
    }

    @Override
    public void changePassword(PasswordDTO passwordDTO) {
        try{
            User user = userRepository.findOneById(passwordDTO.getUser_id());
            if(passwordEncoder.matches(passwordDTO.getOldPassword(), user.getPassword())){
                user.setPassword(passwordEncoder.encode(passwordDTO.getNewPassword()));
                userRepository.save(user);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
