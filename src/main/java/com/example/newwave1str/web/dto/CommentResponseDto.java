package com.example.newwave1str.web.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class CommentResponseDto {
    private Long id;
    private String content;
    private String authorEmail; // 댓글 작성자의 이메일
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}