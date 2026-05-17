package com.stickerprinting.business.auth;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.stickerprinting.business.exception.AuthException;
import com.stickerprinting.business.exception.BadRequestException;
import com.stickerprinting.business.security.JwtService;
import com.stickerprinting.business.user.User;
import com.stickerprinting.business.user.UserRepository;
import com.stickerprinting.business.user.UserResponseDTO;
import com.stickerprinting.business.user.UserStatus;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public AuthResponseDTO register(RegisterRequestDTO request) {

        if (userRepository.existsByCompanyEmail(request.getCompanyEmail())) {
            throw new BadRequestException("Email already exists");
        }

        if (userRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            throw new BadRequestException("Phone number already exists");
        }

        User user = User.builder()
                .companyName(request.getCompanyName())
                .companyEmail(request.getCompanyEmail())
                .phoneNumber(request.getPhoneNumber())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .status(UserStatus.ACTIVE)
                .subscriptionExpiry(LocalDate.now().plusYears(1))
                .createdAt(LocalDateTime.now())
                .build();

        userRepository.save(user);

        String token = jwtService.generateToken(user.getId(), user.getRole().name());

        return AuthResponseDTO.builder()
                .token(token)
                .user(mapToResponse(user))
                .build();
    }

    @Override
    public AuthResponseDTO login(LoginRequestDTO request) {

        String input = request.getEmailOrPhone();

        User user = userRepository
                .findByCompanyEmailOrPhoneNumber(input, input)
                .orElseThrow(() -> new AuthException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new AuthException("Invalid email or password");
        }

        if (user.getStatus() == UserStatus.BLOCKED) {
            throw new AuthException("Your account is blocked");
        }

        if (user.getStatus() == UserStatus.PENDING) {
            throw new AuthException("Account not approved yet");
        }

        if (user.getSubscriptionExpiry() == null ||
                LocalDate.now().isAfter(user.getSubscriptionExpiry())) {

            user.setStatus(UserStatus.EXPIRED);
            userRepository.save(user);

            throw new AuthException("Subscription expired");
        }

        String token = jwtService.generateToken(user.getId(), user.getRole().name());

        return AuthResponseDTO.builder()
                .token(token)
                .user(mapToResponse(user))
                .build();
    }

    private UserResponseDTO mapToResponse(User user) {

        return UserResponseDTO.builder()
                .id(user.getId())
                .companyName(user.getCompanyName())
                .companyEmail(user.getCompanyEmail())
                .phoneNumber(user.getPhoneNumber())
                .role(user.getRole().name())
                .status(user.getStatus().name())
                .subscriptionExpiry(user.getSubscriptionExpiry())
                .build();
    }
}