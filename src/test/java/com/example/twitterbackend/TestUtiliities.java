package com.example.twitterbackend;

import com.example.twitterbackend.authentication.RegisterRequest;
import com.example.twitterbackend.user.User;
import com.example.twitterbackend.usersecurity.UserSecurity;

import java.time.LocalDate;

public class TestUtiliities {

    public static User createUserForTestPurposes() {
        return User.builder()
                .nickname("milosz")
                .dateOfBirth(LocalDate.now())
                .build();
    }

    public static UserSecurity createUserSecurityForTestPurposes() {
        return UserSecurity.builder()
                .email("milosz@gmail.com")
                .password("Haslo_123")
                .build();
    }

    public static RegisterRequest createRegisterRequestForTestPurpose1() {
        return RegisterRequest.builder()
                .username("milosz@gmail.com")
                .password("Haslo_123")
                .repeatedPassword("Haslo_123")
                .nickname("milosz")
                .dateOfBirth(LocalDate.now())
                .build();
    }

    public static RegisterRequest createRegisterRequestForTestPurpose2() {
        return RegisterRequest.builder()
                .username("emilka@gmail.com")
                .password("Haslo_123")
                .repeatedPassword("Haslo_123")
                .nickname("emilka")
                .dateOfBirth(LocalDate.now())
                .build();
    }
    public static RegisterRequest createRegisterRequestForTestPurpose3() {
        return RegisterRequest.builder()
                .username("ktos@gmail.com")
                .password("Haslo_123")
                .repeatedPassword("Haslo_123")
                .nickname("ktosiek")
                .dateOfBirth(LocalDate.now())
                .build();
    }
    public static RegisterRequest createRegisterRequestForTestPurpose4() {
        return RegisterRequest.builder()
                .username("edward@gmail.com")
                .password("Haslo_123")
                .repeatedPassword("Haslo_123")
                .nickname("edward")
                .dateOfBirth(LocalDate.now())
                .build();
    }
    public static RegisterRequest createRegisterRequestForTestPurpose5() {
        return RegisterRequest.builder()
                .username("klaudia@gmail.com")
                .password("Haslo_123")
                .repeatedPassword("Haslo_123")
                .nickname("klaudia")
                .dateOfBirth(LocalDate.now())
                .build();
    }
}
