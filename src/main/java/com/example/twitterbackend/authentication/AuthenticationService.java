package com.example.twitterbackend.authentication;

import com.example.twitterbackend.jwt.JwtService;
import com.example.twitterbackend.user.User;
import com.example.twitterbackend.user.UserService;
import com.example.twitterbackend.usersecurity.UserSecurity;
import com.example.twitterbackend.usersecurity.UserSecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserService userService;
    private final UserSecurityService userSecurityService;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public ResponseEntity<AuthenticationResponse> register(RegisterRequest request) {
        ResponseEntity<UserSecurity> userSecurityResponse = userSecurityService.registerUserSecurity(request);

        if (userSecurityResponse.getStatusCode().equals(HttpStatus.CONFLICT)) {
            return ResponseEntity
                    .status(userSecurityResponse.getStatusCode())
                    .header("Problem", String.valueOf(userSecurityResponse.getHeaders().get("Problem")))
                    .build();
        }

        UserSecurity userSecurity = userSecurityResponse.getBody();

        ResponseEntity<User> userResponse = userService.registerUser(request);

        if (userResponse.getStatusCode().equals(HttpStatus.CONFLICT)) {
            return ResponseEntity
                    .status(userSecurityResponse.getStatusCode())
                    .header("Problem", String.valueOf(userSecurityResponse.getHeaders().get("Problem")))
                    .build();
        }

        final String token = jwtService.generateToken(userSecurity);

        AuthenticationResponse authenticationResponse = AuthenticationResponse.builder()
                .token(token)
                .message("Registered")
                .build();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(authenticationResponse);
    }

    public ResponseEntity<AuthenticationResponse> login(AuthenticationRequest request) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.email());
        final String token = jwtService.generateToken(userDetails);
        AuthenticationResponse response = AuthenticationResponse.builder()
                .token(token)
                .message("Authenticated")
                .build();
        return ResponseEntity.ok(response);
    }
}
