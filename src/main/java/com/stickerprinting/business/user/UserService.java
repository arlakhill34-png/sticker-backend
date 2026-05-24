package com.stickerprinting.business.user;

public interface UserService {

    UserResponseDTO getCurrentUser(Long userId);
    
    StickerSizeResponseDTO updateStickerSize(
            Long userId,
            StickerSizeRequest request
    );
}