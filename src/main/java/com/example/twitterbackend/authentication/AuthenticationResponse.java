package com.example.twitterbackend.authentication;

import lombok.Builder;

@Builder
public record AuthenticationResponse(String message, String token) {

}
