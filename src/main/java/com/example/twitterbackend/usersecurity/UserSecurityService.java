package com.example.twitterbackend.usersecurity;

import com.example.twitterbackend.authentication.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UserSecurityService {

    private static final String PASSWORD_PATTERN = "^((?=.+[0-9])(?=.+[a-z])(?=.+[!@#$%^&*_])(?=.*[A-Z])).{8,}";
    private static final String ERROR_HEADER_NAME = "Problem";
    private final UserSecurityRepository userSecurityRepository;
    private final PasswordEncoder passwordEncoder;

    public Optional<UserSecurity> getUserSecurityByEmail(String email) {
        return userSecurityRepository.findByEmail(email);
    }

    public ResponseEntity<UserSecurity> registerUserSecurity(RegisterRequest request) {
        if (isEmailAlreadyTaken(request.username())) {
            String header = "Email already taken";
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .header(ERROR_HEADER_NAME, header)
                    .build();
        }
        if (!comparePasswords(request.password(), request.repeatedPassword())) {
            String header = "Different passwords";
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .header(ERROR_HEADER_NAME, header)
                    .build();
        }
        if (!validatePassword(request.repeatedPassword())) {
            String header = "Password doesn't meet requirements";
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .header(ERROR_HEADER_NAME, header)
                    .build();
        }

        UserSecurity userSecurity = UserSecurity.builder()
                .email(request.username())
                .password(passwordEncoder.encode(request.password()))
                .build();

        UserSecurity savedUserSecurity = userSecurityRepository.save(userSecurity);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedUserSecurity);
    }

    private boolean validatePassword(String password) {
        return Pattern.matches(PASSWORD_PATTERN, password);
    }

    private boolean comparePasswords(String password, String repeatedPassword) {
        return password.equals(repeatedPassword);
    }

    private boolean isEmailAlreadyTaken(String email) {
        return userSecurityRepository.existsByEmail(email);
    }
}
