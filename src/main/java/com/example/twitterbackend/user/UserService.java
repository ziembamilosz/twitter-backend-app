package com.example.twitterbackend.user;

import com.example.twitterbackend.authentication.RegisterRequest;
import com.example.twitterbackend.usersecurity.UserSecurityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private static final int REQUIRED_NICKNAME_LENGTH = 5;
    private static final int REQUIRED_USER_AGE = 15;
    private final UserRepository userRepository;
    private final UserSecurityRepository userSecurityRepository;

    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            return ResponseEntity
                    .notFound()
                    .build();
        }
        return ResponseEntity.ok(users);
    }

    public ResponseEntity<User> getUserById(Integer id) {
        Optional<User> possibleUser = userRepository.findById(id);
        return possibleUser
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<User> registerUser(RegisterRequest request) {
        if (!isNicknameValid(request.nickname())) {
            String header  = "Invalid nickname";
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .header("Problem", header)
                    .build();
        }
        if (!isOldEnough(request.dateOfBirth())) {
            String header  = "User is too young";
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .header("Problem", header)
                    .build();
        }

        User user = User.builder()
                .nickname(request.nickname())
                .dateOfBirth(request.dateOfBirth())
                .build();

        User savedUser = userRepository.save(user);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedUser);
    }

    public ResponseEntity<Void> deleteUserById(Integer id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    private boolean isNicknameValid(String nickname) {
        return nickname.length() > REQUIRED_NICKNAME_LENGTH;
    }

    private boolean isOldEnough(LocalDate dateOfBirth) {
        return Period.between(dateOfBirth, LocalDate.now()).getYears() > REQUIRED_USER_AGE;
    }

}
