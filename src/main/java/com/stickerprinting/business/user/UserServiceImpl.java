package com.stickerprinting.business.user;

import org.springframework.stereotype.Service;

import com.stickerprinting.business.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponseDTO getCurrentUser(Long userId) {
    	
    	User user = userRepository.findById(userId)
    	        .orElseThrow(() ->
    	           new ResourceNotFoundException("User not found"));
    	
        return UserResponseDTO.builder()
                .id(user.getId())
                .companyName(user.getCompanyName())
                .companyEmail(user.getCompanyEmail())
                .phoneNumber(user.getPhoneNumber())
                .role(user.getRole().name())
                .status(user.getStatus().name())
                .subscriptionExpiry(
                        user.getSubscriptionExpiry()
                )
                .build();
    }
}