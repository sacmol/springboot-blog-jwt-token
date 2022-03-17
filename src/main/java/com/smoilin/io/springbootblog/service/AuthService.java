package com.smoilin.io.springbootblog.service;

import com.smoilin.io.springbootblog.dto.LoginRequest;
import com.smoilin.io.springbootblog.dto.RegisterRequest;
import com.smoilin.io.springbootblog.model.User;
import com.smoilin.io.springbootblog.repository.UserRepository;
import com.smoilin.io.springbootblog.security.JwtProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
    }

    public void signup(RegisterRequest registerRequest) {
        User user = new User();
        user.setUserName(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(encodePassword(registerRequest.getPassword()));

        userRepository.save(user);

    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    public AuthenticationResponse login(LoginRequest loginRequest) {
        Authentication authenticate = authenticationManager.authenticate((new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                loginRequest.getPassword())));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String authenticationToken = jwtProvider.generateToken(authenticate);
        //return jwtProvider.generateToken(authenticate);
        return new AuthenticationResponse(authenticationToken, loginRequest.getUsername());
    }

    public Optional<org.springframework.security.core.userdetails.User> getCurrentUser() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User)SecurityContextHolder.
                getContext().getAuthentication().getPrincipal();
        return Optional.of(principal);
    }
}
