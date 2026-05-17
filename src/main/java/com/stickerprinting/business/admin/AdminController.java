package com.stickerprinting.business.admin;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stickerprinting.business.payload.ApiResponse;
import com.stickerprinting.business.user.UserResponseDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/users")
    public ResponseEntity<ApiResponse<List<UserResponseDTO>>> getAllUsers() {

        return ResponseEntity.ok(
                ApiResponse.<List<UserResponseDTO>>builder()
                        .success(true)
                        .message("Users fetched successfully")
                        .data(adminService.getAllUsers())
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @PutMapping("/users/{id}/status")
    public ResponseEntity<ApiResponse<UserResponseDTO>> updateStatus(
            @PathVariable Long id,
            @RequestBody UpdateUserStatusRequest request
    ) {

        return ResponseEntity.ok(
                ApiResponse.<UserResponseDTO>builder()
                        .success(true)
                        .message("User status updated")
                        .data(adminService.updateUserStatus(id, request.getStatus()))
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @PutMapping("/users/{id}/extend")
    public ResponseEntity<ApiResponse<UserResponseDTO>> extendSubscription(
            @PathVariable Long id,
            @RequestBody ExtendSubscriptionRequest request
    ) {

        return ResponseEntity.ok(
                ApiResponse.<UserResponseDTO>builder()
                        .success(true)
                        .message("Subscription extended")
                        .data(adminService.extendSubscription(id, request.getDays()))
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<ApiResponse<String>> deleteUser(
            @PathVariable Long id
    ) {

        adminService.deleteUser(id);

        return ResponseEntity.ok(
                ApiResponse.<String>builder()
                        .success(true)
                        .message("User deleted successfully")
                        .data("Deleted")
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
}
