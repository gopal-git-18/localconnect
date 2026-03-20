package com.localconnect.controller;

import com.localconnect.dto.response.UserResponse;
import com.localconnect.entity.User;
import com.localconnect.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    // Get my profile
    @GetMapping("/me")
    public ResponseEntity<UserResponse> getMyProfile() {
        return ResponseEntity.ok(userService.getMyProfile());
    }

    // Update my profile
    @PutMapping("/me")
    public ResponseEntity<UserResponse> updateProfile(
            @RequestBody User updatedData) {
        return ResponseEntity.ok(userService.updateProfile(updatedData));
    }

    // Change password
    @PutMapping("/change-password")
    public ResponseEntity<String> changePassword(
            @RequestBody Map<String, String> request) {
        String oldPassword = request.get("oldPassword");
        String newPassword = request.get("newPassword");
        return ResponseEntity.ok(
                userService.changePassword(oldPassword, newPassword));
    }

    // Admin - get all users
    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // Admin - block/unblock user
    @PutMapping("/toggle/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> toggleUserStatus(
            @PathVariable Integer userId) {
        return ResponseEntity.ok(userService.toggleUserStatus(userId));
    }
}