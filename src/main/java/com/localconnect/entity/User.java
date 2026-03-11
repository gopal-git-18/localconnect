package com.localconnect.entity;

import jakarta.persistence.*;
import lombok.Data;
import com.localconnect.enums.Roles;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;
    private String password;
    private String phone;

    @Enumerated(EnumType.STRING)
    private Roles role;

    private String city;
    private String state;
    private String pincode;
    private String profileImage;

    private Boolean isActive = true;

    private LocalDateTime createdAt = LocalDateTime.now();


}
