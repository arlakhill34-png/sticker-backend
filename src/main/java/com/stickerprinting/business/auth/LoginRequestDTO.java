package com.stickerprinting.business.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDTO {

    @NotBlank
    private String emailOrPhone;

    @NotBlank
    private String password;
}