package com.localconnect.service;

import com.localconnect.config.JwtUtil;
import com.localconnect.dto.request.LoginRequest;
import com.localconnect.dto.request.RegisterRequest;
import com.localconnect.dto.response.AuthResponse;
import com.localconnect.entity.User;
import com.localconnect.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    // REGISTER
    public AuthResponse register(RegisterRequest request) {

        // Check if email already exists
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered!");
        }

        // Create new user
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPhone(request.getPhone());
        user.setRole(request.getRole());
        user.setCity(request.getCity());
        user.setState(request.getState());
        user.setPincode(request.getPincode());

        userRepository.save(user);

        // Generate token
        String token = jwtUtil.generateToken(user.getEmail());

        return new AuthResponse(
                token,
                user.getName(),
                user.getEmail(),
                user.getRole().name(),
                user.getCity()
        );
    }

    // LOGIN
    public AuthResponse login(LoginRequest request) {

        // Authenticate email + password
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        // Load user
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found!"));

        // Generate token
        String token = jwtUtil.generateToken(user.getEmail());

        return new AuthResponse(
                token,
                user.getName(),
                user.getEmail(),
                user.getRole().name(),
                user.getCity()
        );
    }
}