package com.stickerprinting.business.admin;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.stickerprinting.business.auth.Role;
import com.stickerprinting.business.user.User;
import com.stickerprinting.business.user.UserRepository;
import com.stickerprinting.business.user.UserStatus;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        Optional<User> adminOpt =
                userRepository.findByCompanyEmail("admin@system.com");

        if (adminOpt.isEmpty()) {

            User admin = User.builder()
                    .companyName("SYSTEM ADMIN")
                    .companyEmail("admin@system.com")
                    .phoneNumber("9800000000")
                    .password(passwordEncoder.encode("admin123"))
                    .role(Role.ADMIN)
                    .status(UserStatus.ACTIVE)
                    .subscriptionExpiry(LocalDate.now().plusYears(100))
                    .createdAt(LocalDateTime.now())
                    .build();

            userRepository.save(admin);
        }
    }
}
