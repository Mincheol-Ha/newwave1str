package com.example.newwave1str.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentLikeRequestDto {

    private Long commentId;
    private String postId;
    private String email;

}
