package com.example.newwave1str.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostLikeResponseDto {

    private Long postId;
    private String email;
    private boolean liked;
    private String message;

}
