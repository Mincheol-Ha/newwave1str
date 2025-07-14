package com.example.newwave1str.controller;

import com.example.newwave1str.jwt.JwtTokenProvider;
import com.example.newwave1str.service.CommentService;
import com.example.newwave1str.web.dto.CommentRequestDto;
import com.example.newwave1str.web.dto.CommentResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "comment-controller", description = "댓글 관련 API (작성, 수정, 삭제)")
@RestController
@RequestMapping("/api/post/{postId}/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final JwtTokenProvider jwtTokenProvider;

    @Operation(summary = "댓글 작성", description = "특정 게시글에 댓글을 작성합니다.")
    @PostMapping
    public ResponseEntity<CommentResponseDto> createComment(@PathVariable Long postId,
                                                            @RequestHeader("Authorization") String token,
                                                            @RequestBody CommentRequestDto requestDto) {
        String email = extractEmailFromToken(token);
        CommentResponseDto response = commentService.createComment(postId, email, requestDto);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "댓글 수정", description = "특정 게시글에 댓글을 수정합니다.")
    @PutMapping("/{commentId}")
    public ResponseEntity<CommentResponseDto> updateComment(@PathVariable Long postId,
                                                            @PathVariable Long commentId,
                                                            @RequestHeader("Authorization") String token,
                                                            @RequestBody CommentRequestDto requestDto) {
        String email = extractEmailFromToken(token);
        CommentResponseDto response = commentService.updateComment(commentId, email, requestDto);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "댓글 삭제", description = "특정 게시글에 댓글을 삭제합니다.")
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long postId,
                                              @PathVariable Long commentId,
                                              @RequestHeader("Authorization") String token) {
        String email = extractEmailFromToken(token);
        commentService.deleteComment(commentId, email);
        return ResponseEntity.noContent().build();
    }

    // JWT 토큰에서 이메일 추출
    private String extractEmailFromToken(String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        return jwtTokenProvider.getEmailFromToken(token);
    }
}