package com.example.ISA_project.service.implementation;

import com.example.ISA_project.Token;
import com.example.ISA_project.model.Patient;
import com.example.ISA_project.model.PatientCategory;
import com.example.ISA_project.model.PatientChart;
import com.example.ISA_project.model.User;
import com.example.ISA_project.model.dto.LoginRequest;
import com.example.ISA_project.model.dto.RegistrationRequest;
import com.example.ISA_project.model.dto.UserResponse;
import com.example.ISA_project.repository.UserRepository;
import com.example.ISA_project.service.IAuthService;
import com.example.ISA_project.service.IPatientChartService;
import com.example.ISA_project.service.IPatientService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthService implements IAuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final Token token;
    private IPatientService patientService;
    private IPatientChartService patientChartService;

    public AuthService(PasswordEncoder passwordEncoder,UserRepository userRepository, Token token, IPatientService patientService, IPatientChartService patientChartService){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.token = token;
        this.patientService = patientService;
        this.patientChartService = patientChartService;
    }

    @Override
    public UserResponse login(LoginRequest request) {
        if (userRepository.findOneByEmail(request.getEmail()) == null){
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
        //PatientChart pc = new PatientChart();
        //patientChartService.save(pc);
        Patient patient = new Patient();
        patient.setUser(request);
        patient.setPatientChart(new PatientChart());
        patient.setPatientCategory(PatientCategory.REGULAR);
        patient.setLoyaltyPoints(0);
        List<LocalDate> points = new ArrayList<>();
        //points.add(LocalDateTime.of(1998, 5,20));
        patient.setPenaltyPoints(points);
        //System.out.println(patient.getPenaltyPoints());
        if(patientService.save(patient)!= null)
            return mapUserToUserResponse(request);
        else return null;
    }

    private UserResponse mapUserToUserResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setUserRoles(user.getUserType().toString());
        userResponse.setEmail(user.getEmail());
        userResponse.setActivated(user.isPasswordChanged());
        return userResponse;
    }
}
