package com.localconnect.dto.response;

import lombok.Data;

@Data
public class UserResponse {
    private Integer id;
    private String name;
    private String email;
    private String phone;
    private String role;
    private String city;
    private String state;
    private String pincode;
    private String profileImage;
    private Boolean isActive;
}