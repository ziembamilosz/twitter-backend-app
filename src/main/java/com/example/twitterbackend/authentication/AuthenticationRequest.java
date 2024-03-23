package com.example.twitterbackend.authentication;

import lombok.Builder;

@Builder
public record AuthenticationRequest(String email, String password) {
}
