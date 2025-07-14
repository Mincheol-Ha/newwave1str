package com.example.newwave1str.service;

import com.example.newwave1str.repository.CommentJpaRepository;
import com.example.newwave1str.repository.CommentLikeJpaRepository;
import com.example.newwave1str.repository.PostJpaRepository;
import com.example.newwave1str.repository.PostLikeJpaRepository;
import com.example.newwave1str.web.dto.CommentLikeResponseDto;
import com.example.newwave1str.web.dto.PostLikeResponseDto;
import com.example.newwave1str.web.entity.CommentEntity;
import com.example.newwave1str.web.entity.CommentLikeEntity;
import com.example.newwave1str.web.entity.PostEntity;
import com.example.newwave1str.web.entity.PostLikeEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentLikeService {

    private final CommentLikeJpaRepository commentLikeJpaRepository;
    private final CommentJpaRepository commentJpaRepository;

    @Transactional
    public CommentLikeResponseDto toggleLike(Long commentId, String email) {
        CommentEntity comment = commentJpaRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("댓글 없음"));

        Optional<CommentLikeEntity> existingLike = commentLikeJpaRepository.findByCommentAndEmail(comment, email);

        if (existingLike.isPresent()) {

            commentLikeJpaRepository.delete(existingLike.get());
            return CommentLikeResponseDto.builder()
                    .commentId(commentId)
                    .email(email)
                    .liked(false)
                    .message("댓글 좋아요👎 취소!")
                    .build();
        } else {

            CommentLikeEntity newLike = CommentLikeEntity.builder()
                    .comment(comment)
                    .email(email)
                    .build();
            commentLikeJpaRepository.save(newLike);
            return CommentLikeResponseDto.builder()
                    .commentId(comment.getId())
                    .email(email)
                    .liked(true)
                    .message("댓글 좋아요👍 등록!!")
                    .build();
        }
    }
}
