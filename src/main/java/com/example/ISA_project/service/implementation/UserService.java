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
       Address address= user.getAddress();
        address.setStreet(profile.getAddress());
        address.setState(profile.getState());
        address.setTown(profile.getTown());
        user.setPhone(profile.getPhone());
        user.setName(profile.getName());
        user.setSurname(profile.getSurname());
        user.setEmail(profile.getEmail());
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

    public ProfileDTO getProfile(String username) {
        User user = userRepository.findOneByEmail(username);
        ProfileDTO profile = new ProfileDTO(user.getName(), user.getSurname(),
                user.getEmail(), user.getAddress().getStreet(), user.getAddress().getState(),
                user.getAddress().getTown(), user.getPhone(), "GOLD",user.getDateOfBirth());

        return profile;
    }

    public User findUserByEmail(String email){
        return userRepository.findOneByEmail(email);
    }

    @Override
    public void changePassword(PasswordDTO passwordDTO) {
        try{
            User user = userRepository.findOneByEmail(passwordDTO.getEmail());
            if(passwordEncoder.matches(passwordDTO.getOldPassword(), user.getPassword())){
                user.setPassword(passwordEncoder.encode(passwordDTO.getNewPassword()));
                if(!user.isPasswordChanged())
                    user.setPasswordChanged(true);
                userRepository.save(user);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}
