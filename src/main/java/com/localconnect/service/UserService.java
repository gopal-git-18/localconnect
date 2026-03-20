package com.localconnect.service;

import com.localconnect.dto.response.UserResponse;
import com.localconnect.entity.User;
import com.localconnect.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Get currently logged in user
    public User getCurrentUser() {
        String email = SecurityContextHolder.getContext()
                .getAuthentication().getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found!"));
    }

    // Get my profile
    public UserResponse getMyProfile() {
        return mapToResponse(getCurrentUser());
    }

    // Update my profile
    public UserResponse updateProfile(User updatedData) {
        User user = getCurrentUser();
        user.setName(updatedData.getName());
        user.setPhone(updatedData.getPhone());
        user.setCity(updatedData.getCity());
        user.setState(updatedData.getState());
        user.setPincode(updatedData.getPincode());
        userRepository.save(user);
        return mapToResponse(user);
    }

    // Change password
    public String changePassword(String oldPassword, String newPassword) {
        User user = getCurrentUser();
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new RuntimeException("Old password is incorrect!");
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        return "Password changed successfully!";
    }

    // Admin - get all users
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Admin - block/unblock user
    public String toggleUserStatus(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found!"));
        user.setIsActive(!user.getIsActive());
        userRepository.save(user);
        return user.getIsActive() ? "User unblocked!" : "User blocked!";
    }

    // Map entity to response DTO
    public UserResponse mapToResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setPhone(user.getPhone());
        response.setRole(user.getRole().name());
        response.setCity(user.getCity());
        response.setState(user.getState());
        response.setPincode(user.getPincode());
        response.setProfileImage(user.getProfileImage());
        response.setIsActive(user.getIsActive());
        return response;
    }
}