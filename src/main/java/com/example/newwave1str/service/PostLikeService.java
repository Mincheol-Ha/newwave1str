package com.example.newwave1str.service;

import com.example.newwave1str.repository.PostJpaRepository;
import com.example.newwave1str.repository.PostLikeJpaRepository;
import com.example.newwave1str.web.dto.PostLikeResponseDto;
import com.example.newwave1str.web.entity.PostEntity;
import com.example.newwave1str.web.entity.PostLikeEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostLikeService {

    private final PostLikeJpaRepository postLikeJpaRepository;
    private final PostJpaRepository postJpaRepository;

    @Transactional
    public PostLikeResponseDto toggleLike(Long postId, String email) {
        PostEntity post = postJpaRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글 없음"));

        Optional<PostLikeEntity> existingLike = postLikeJpaRepository.findByPostAndEmail(post, email);

        if (existingLike.isPresent()) {

            postLikeJpaRepository.delete(existingLike.get());
            return PostLikeResponseDto.builder()
                    .postId(postId)
                    .email(email)
                    .liked(false)
                    .message("게시글 좋아요👎 취소!")
                    .build();
        } else {
            PostLikeEntity newLike = PostLikeEntity.builder()
                    .post(post)
                    .email(email)
                    .build();
            postLikeJpaRepository.save(newLike);
            return PostLikeResponseDto.builder()
                    .postId(postId)
                    .email(email)
                    .liked(true)
                    .message("게시글 좋아요👍 등록!")
                    .build();

        }
    }
}
