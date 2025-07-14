package com.example.newwave1str.controller;

import com.example.newwave1str.jwt.JwtTokenProvider;
import com.example.newwave1str.service.CommentLikeService;
import com.example.newwave1str.service.CommentService;
import com.example.newwave1str.service.PostLikeService;
import com.example.newwave1str.web.dto.CommentLikeResponseDto;
import com.example.newwave1str.web.dto.PostLikeResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "like-controller", description = "좋아요 관련 API (추가, 삭제)")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LikeController {

    private final PostLikeService postLikeService;
    private final JwtTokenProvider jwtTokenProvider;
    private final CommentLikeService commentLikeService;


    @Operation(summary = "좋아요 추가, 삭제", description = "특정 게시글에 좋아요를 추가또는 삭제합니다.")
    @PostMapping("/post/{postId}/like")
    public PostLikeResponseDto togglePostLike(
            @PathVariable Long postId,
            @RequestHeader("Authorization") String token) {
        String email = extractEmailFromToken(token);
        return postLikeService.toggleLike(postId, email);

    }

    @Operation(summary = "좋아요 추가, 삭제", description = "특정 게시글에 댓글의 좋아요를 추가또는 삭제합니다.")
    @PostMapping("/comment/{commentId}/like")
    public CommentLikeResponseDto toggleCommentLike(
            @PathVariable Long commentId,
            @RequestHeader("Authorization") String token) {
        String email = extractEmailFromToken(token);
        return commentLikeService.toggleLike(commentId, email);

    }

    private String extractEmailFromToken(String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        return jwtTokenProvider.getEmailFromToken(token);
    }
}
