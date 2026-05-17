package com.stickerprinting.business.admin;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.stickerprinting.business.exception.ResourceNotFoundException;
import com.stickerprinting.business.user.User;
import com.stickerprinting.business.user.UserRepository;
import com.stickerprinting.business.user.UserResponseDTO;
import com.stickerprinting.business.user.UserStatus;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;

    @Override
    public List<UserResponseDTO> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public UserResponseDTO updateUserStatus(Long userId, UserStatus status) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        user.setStatus(status);

        userRepository.save(user);

        return mapToDTO(user);
    }

    @Override
    public UserResponseDTO extendSubscription(Long userId, int days) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        if (user.getSubscriptionExpiry() == null) {
            user.setSubscriptionExpiry(LocalDate.now());
        }

        user.setSubscriptionExpiry(
                user.getSubscriptionExpiry().plusDays(days)
        );

        userRepository.save(user);

        return mapToDTO(user);
    }

    @Override
    public void deleteUser(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        userRepository.delete(user);
    }

    private UserResponseDTO mapToDTO(User user) {

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