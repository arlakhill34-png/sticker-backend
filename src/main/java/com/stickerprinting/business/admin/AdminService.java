package com.stickerprinting.business.admin;

import java.util.List;

import com.stickerprinting.business.user.UserResponseDTO;
import com.stickerprinting.business.user.UserStatus;

public interface AdminService {

    List<UserResponseDTO> getAllUsers();

    UserResponseDTO updateUserStatus(Long userId, UserStatus status);

    UserResponseDTO extendSubscription(Long userId, int days);

    void deleteUser(Long userId);
}