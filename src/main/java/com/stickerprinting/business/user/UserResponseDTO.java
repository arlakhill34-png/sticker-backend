package com.stickerprinting.business.user;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDTO {

    private Long id;

    private String companyName;

    private String companyEmail;

    private String phoneNumber;

    private String role;

    private String status;

    private LocalDate subscriptionExpiry;
}