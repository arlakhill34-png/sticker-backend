package com.stickerprinting.business.admin;

import com.stickerprinting.business.user.UserStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserStatusRequest {

    private UserStatus status;
}
