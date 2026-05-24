package com.stickerprinting.business.user;

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
public class StickerSizeResponseDTO {

    private Double stickerWidth;

    private Double stickerHeight;
}