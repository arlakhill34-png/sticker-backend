package com.stickerprinting.business.user;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stickerprinting.business.payload.ApiResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

//    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/me")
    public ResponseEntity<ApiResponse<UserResponseDTO>> getCurrentUser
                       (Authentication auth) {
    	
    	System.out.println("AUTH: " + auth);
    	System.out.println("ROLES: " + auth.getAuthorities());
    	System.out.println("NAME: " + auth.getName());
    	Long userId = Long.valueOf(auth.getName());
    	
        UserResponseDTO user =
                userService.getCurrentUser(userId);

        ApiResponse<UserResponseDTO> response =
                ApiResponse.<UserResponseDTO>builder()
                        .success(true)
                        .message("User fetched successfully")
                        .data(user)
                        .timestamp(LocalDateTime.now())
                        .build();

        return ResponseEntity.ok(response);
    }
    
    @PutMapping("/sticker-size")
    public ResponseEntity<ApiResponse<StickerSizeResponseDTO>>
    updateStickerSize(
            Authentication auth,
            @RequestBody StickerSizeRequest request
    ) {

        Long userId = Long.valueOf(auth.getName());

        StickerSizeResponseDTO responseData =
                userService.updateStickerSize(userId, request);

        ApiResponse<StickerSizeResponseDTO> response =
                ApiResponse.<StickerSizeResponseDTO>builder()
                        .success(true)
                        .message("Sticker size updated successfully")
                        .data(responseData)
                        .timestamp(LocalDateTime.now())
                        .build();

        return ResponseEntity.ok(response);
    }
}
