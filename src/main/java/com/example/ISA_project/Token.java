package com.example.ISA_project;

import com.example.ISA_project.model.User;
import com.example.ISA_project.service.*;
import com.example.ISA_project.service.implementation.*;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import com.example.ISA_project.model.UserType;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Getter
public class Token {
    private final IUserService userService;
    private final IDermatologistService dermatologistService;
    private final IPharmacistService pharmacistService;
    private final ISupplierService supplierService;
    private final IPatientService patientService;
    private final IPharmacyAdminService pharmacyAdminService;

    @Value("ISA_project")
    private String APP_NAME;

    @Value("1200000")
    private int EXPIRES_IN;

    @Value("somesecret")
    private String SECRET;

    private UserType userTypes;

    static final String AUDIENCE_WEB = "web";

    private SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;

    public Token(IUserService userService, IDermatologistService dermatologistService, IPharmacistService pharmacistService, ISupplierService supplierService, IPatientService patientService, IPharmacyAdminService pharmacyAdminService) {
        this.userService = userService;
        this.dermatologistService = dermatologistService;
        this.patientService = patientService;
        this.pharmacistService = pharmacistService;
        this.supplierService = supplierService;
        this.pharmacyAdminService = pharmacyAdminService;
    }

    private Date generateExpirationDate() {
        return new Date(new Date().getTime() + EXPIRES_IN);
    }

    public String generateToken(String email) {
        User user = userService.findUserByEmail(email);
        //System.out.println(user);
        int user_id;
        int pharmacyId = 0;
        if(user.getUserType() == UserType.Dermatologist){
            user_id = dermatologistService.getByUserId(user.getId()).getId();

        }else if(user.getUserType() == UserType.Pharmacist){
            user_id = pharmacistService.getByUserId(user.getId()).getId();
            pharmacyId = pharmacistService.getByUserId(user.getId()).getPharmacy().getId();
        }else if(user.getUserType() == UserType.Patient){
            user_id = patientService.getByUserId(user.getId()).getId();
        }else if(user.getUserType() == UserType.PharmacyAdministrator){
            user_id = pharmacyAdminService.getByUserId(user.getId()).getId();
            pharmacyId =pharmacyAdminService.getByUserId(user.getId()).getPharmacy().getId();
        }else if(user.getUserType() == UserType.Supplier){
            user_id = supplierService.getByUserId(user.getId()).getId();
        }else
            user_id = user.getId(); //za systemAdmina, on nema drugih polja osim usera

        return Jwts.builder()
                .setIssuer(APP_NAME)
                .setSubject(email)
                .setAudience(AUDIENCE_WEB)
                .setIssuedAt(new Date())
                .setExpiration(generateExpirationDate())
                .claim("email", email)
                .claim("user_id", user_id)
                .claim("user_type", user.getUserType())
                .claim("pharmacyId", pharmacyId)
                .signWith(SIGNATURE_ALGORITHM, SECRET).compact();
    }

    // Funkcija za refresh JWT token
    public String refreshToken(String token) {
        String refreshedToken;
        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            claims.setIssuedAt(new Date());
            refreshedToken = Jwts.builder()
                    .setClaims(claims)
                    .setExpiration(generateExpirationDate())
                    .signWith(SIGNATURE_ALGORITHM, SECRET).compact();
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    // Funkcija za citanje svih podataka iz JWT tokena
    private Claims getAllClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    public UserType getRoleFromToken(String token) {
        UserType userType;
        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            userType = claims.get("user_type", UserType.class);
        } catch (Exception e) {
            userType = null;
        }
        return userType;
    }
}
