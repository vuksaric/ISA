package com.example.ISA_project;

import com.example.ISA_project.model.User;
import com.example.ISA_project.service.implementation.UserService;
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
    private final UserService userService;

    @Value("ISA_project")
    private String APP_NAME;

    @Value("1200000")
    private int EXPIRES_IN;

    @Value("somesecret")
    private String SECRET;

    private UserType userTypes;

    static final String AUDIENCE_WEB = "web";

    private SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;

    public Token(UserService userService) {
        this.userService = userService;
    }

    private Date generateExpirationDate() {
        return new Date(new Date().getTime() + EXPIRES_IN);
    }

    public String generateToken(String username) {
        User user = userService.findUserByEmail(username);

        return Jwts.builder()
                .setIssuer(APP_NAME)
                .setSubject(username)
                .setAudience(AUDIENCE_WEB)
                .setIssuedAt(new Date())
                .setExpiration(generateExpirationDate())
                .claim("username", username)
                .claim("user_id", user.getId())
                .claim("user_type", user.getUserType())
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
