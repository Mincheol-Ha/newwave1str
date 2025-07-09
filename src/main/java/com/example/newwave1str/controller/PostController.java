package com.example.newwave1str.controller;

import com.example.newwave1str.jwt.JwtTokenProvider;
import com.example.newwave1str.service.PostService;
import com.example.newwave1str.web.dto.PostRequestDto;
import com.example.newwave1str.web.dto.PostResponseDto;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;


import java.util.List;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final JwtTokenProvider jwtTokenProvider;

    // 1. 전체 조회
    @GetMapping
    public List<PostResponseDto> getAllPosts() {
        return postService.findAll();
    }

    // 2. 이메일로 조회
    @GetMapping("/by-email")
    public List<PostResponseDto> getPostsByEmail(@RequestParam String email) {
        return postService.findByEmail(email);
    }

    // 3. 생성
    @PostMapping
    public PostResponseDto write (@RequestHeader("Authorization") String token,
                                  @RequestBody PostRequestDto requestDto) {
        String email = extractEmailFromToken(token);
        return postService.create(email, requestDto);
    }

    // 4. 수정
    @PutMapping("/{id}")
    public PostResponseDto updatePost(@PathVariable Long id,
                                      @RequestBody PostRequestDto requestDto) {
        return postService.update(id, requestDto);
    }

    // 5. 삭제
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
