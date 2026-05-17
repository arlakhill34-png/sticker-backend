package com.stickerprinting.business.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequestDTO {

    @NotBlank
    private String companyName;

    @Email
    private String companyEmail;
    
    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String password;
}