package com.gym.security.controller;

import com.gym.security.configuration.JWTConfiguration;
import com.gym.security.dto.AuthRequest;
import com.gym.security.dto.AuthResponse;
import com.gym.security.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationManager manager;

    private final TokenProvider provider;

    private final UserDetailsService userProvider;

    @PostMapping
    public ResponseEntity<AuthResponse> loginUser(@RequestBody AuthRequest request) {

        Authentication authenticate = manager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmailUser(),
                        request.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authenticate);

        /*Generate token with answer to user*/
        return ResponseEntity.ok(
                AuthResponse
                        .builder()
                        .emailUser(request.getEmailUser())
                        .token(
                                provider.generateToken(
                                        userProvider.loadUserByUsername(request.getEmailUser())
                                )
                        )
                        .build()
        );
    }
}
