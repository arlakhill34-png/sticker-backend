package com.stickerprinting.business.user;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.stickerprinting.business.auth.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String companyName;

    @Column(nullable = false, unique = true)
    private String companyEmail;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    private LocalDate subscriptionExpiry;

    private LocalDateTime createdAt;

    @Column(nullable = false)
    @Builder.Default
    private Double stickerWidth = 36.0;

    @Column(nullable = false)
    @Builder.Default
    private Double stickerHeight = 30.0;
}