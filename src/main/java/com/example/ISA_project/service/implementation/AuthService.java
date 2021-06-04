package com.example.ISA_project.service.implementation;

import com.example.ISA_project.Token;
import com.example.ISA_project.model.User;
import com.example.ISA_project.model.dto.LoginRequest;
import com.example.ISA_project.model.dto.RegistrationRequest;
import com.example.ISA_project.model.dto.UserResponse;
import com.example.ISA_project.repository.UserRepository;
import com.example.ISA_project.service.IAuthService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements IAuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final Token token;

    public AuthService(PasswordEncoder passwordEncoder,UserRepository userRepository, Token token){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.token = token;
    }

    @Override
    public UserResponse login(LoginRequest request) {
        if (userRepository.findOneByEmail(request.getEmail()) == null){
            System.out.println("usaooo");
            return null;
        }
        User user = userRepository.findOneByEmail(request.getEmail());
        if (user == null){
            return null;
        }
        String jwt = "";
        int expiresIn = 0;
        jwt = token.generateToken(request.getEmail());
        expiresIn = token.getEXPIRES_IN();

        UserResponse userResponse = mapUserToUserResponse(user);
        userResponse.setToken(jwt);
        userResponse.setTokenExpiresIn(expiresIn);
        if(passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return userResponse;
        }
        else{
            return null;
        }
    }

    @Override
    public UserResponse registerSubject(User request) {
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        User savedUser = userRepository.save(request);
        return mapUserToUserResponse(savedUser);
    }

    private UserResponse mapUserToUserResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setUserRoles(user.getUserType().toString());
        userResponse.setEmail(user.getEmail());
        return userResponse;
    }
}
