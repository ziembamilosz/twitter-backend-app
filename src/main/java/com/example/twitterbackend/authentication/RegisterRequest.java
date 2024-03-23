package com.example.twitterbackend.authentication;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record RegisterRequest(String nickname,
                              LocalDate dateOfBirth,
                              String username,
                              String password,
                              String repeatedPassword) {}