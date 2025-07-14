package com.example.newwave1str.controller;

import com.example.newwave1str.jwt.JwtTokenProvider;
import com.example.newwave1str.service.PostService;
import com.example.newwave1str.web.dto.PostRequestDto;
import com.example.newwave1str.web.dto.PostResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;


import java.util.List;

@Tag(name = "post-controller", description = "게시글 관련 API (작성, 수정, 조회, 삭제)")
@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final JwtTokenProvider jwtTokenProvider;

    @Operation(summary = "전체 게시글 조회", description = "작성된 게시글 전체를 조회합니다.")
    @GetMapping
    public List<PostResponseDto> getAllPosts() {
        return postService.findAll();
    }

    @Operation(summary = "이메일로 게시글 조회", description = "특정 이메일로 작성된 게시글 전체를 조회합니다.")
    @GetMapping("/by-email")
    public List<PostResponseDto> getPostsByEmail(@RequestParam String email) {
        return postService.findByEmail(email);
    }

    @Operation(summary = "게시글 쓰기", description = "게시글을 작성합니다")
    @PostMapping
    public PostResponseDto write (@RequestHeader("Authorization") String token,
                                  @RequestBody PostRequestDto requestDto) {
        String email = extractEmailFromToken(token);
        return postService.create(email, requestDto);
    }

    @Operation(summary = "게시글 수정", description = "작성된 게시글을 수정합니다.")
    @PutMapping("/{id}")
    public PostResponseDto updatePost(@PathVariable Long id,
                                      @RequestBody PostRequestDto requestDto) {
        return postService.update(id, requestDto);
    }

    // 5. 삭제
    @Operation(summary = "게시글 삭제", description = "작성된 게시글을 삭제합니다.")
    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.delete(id);
    }

    private String extractEmailFromToken(String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        return jwtTokenProvider.getEmailFromToken(token);
    }
}
