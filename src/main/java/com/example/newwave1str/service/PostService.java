package com.example.newwave1str.service;

import com.example.newwave1str.repository.PostJpaRepository;
import com.example.newwave1str.web.dto.PostRequestDto;
import com.example.newwave1str.web.dto.PostResponseDto;
import com.example.newwave1str.web.entity.PostEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostJpaRepository postJpaRepository;

    // 전체 조회
    public List<PostResponseDto> findAll() {
        return postJpaRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // 이메일로 조회
    public List<PostResponseDto> findByEmail(String email) {
        return postJpaRepository.findByAuthorEmail(email).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // 게시물 생성
    public PostResponseDto create(String email, PostRequestDto requestDto) {
        PostEntity post = PostEntity.builder()
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .authorEmail(email)
                .build();

        return toDto(postJpaRepository.save(post));
    }

    // 게시물 수정
    public PostResponseDto update(Long id, PostRequestDto requestDto) {
        PostEntity post = postJpaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시물이 존재하지 않습니다."));

        PostEntity updated = PostEntity.builder()
                .id(post.getId())
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .authorEmail(post.getAuthorEmail())
                .createdAt(post.getCreatedAt())
                .build();

        return toDto(postJpaRepository.save(updated));
    }

    // 게시물 삭제
    public void delete(Long id) {
        postJpaRepository.deleteById(id);
    }

    private PostResponseDto toDto(PostEntity entity) {
        return PostResponseDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .authorEmail(entity.getAuthorEmail())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}
