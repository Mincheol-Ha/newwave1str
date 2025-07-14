package com.example.newwave1str.service;

import com.example.newwave1str.repository.CommentJpaRepository;
import com.example.newwave1str.repository.PostJpaRepository;
import com.example.newwave1str.repository.UserJpaRepository;
import com.example.newwave1str.web.dto.CommentRequestDto;
import com.example.newwave1str.web.dto.CommentResponseDto;
import com.example.newwave1str.web.entity.CommentEntity;
import com.example.newwave1str.web.entity.PostEntity;
import com.example.newwave1str.web.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentJpaRepository commentJpaRepository;
    private final PostJpaRepository postJpaRepository;
    private final UserJpaRepository userJpaRepository;

    //댓글 생성
    @Transactional
    public CommentResponseDto createComment(Long postId, String userEmail, CommentRequestDto requestDto) {
        PostEntity post = postJpaRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

        UserEntity user = userJpaRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));

        CommentEntity comment = CommentEntity.builder()
                .content(requestDto.getContent())
                .user(user)
                .post(post)
                .build();

        CommentEntity saved = commentJpaRepository.save(comment);
        return toDto(saved);
    }

    //댓글 수정
    @Transactional
    public CommentResponseDto updateComment(Long commentId, String userEmail, CommentRequestDto requestDto) {
        CommentEntity comment = commentJpaRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));

        if (!comment.getUser().getEmail().equals(userEmail)) {
            throw new IllegalStateException("댓글 작성자만 수정할 수 있습니다.");
        }

        comment.setContent(requestDto.getContent());
        return toDto(comment);
    }

    //댓글 삭제
    @Transactional
    public void deleteComment(Long commentId, String userEmail) {
        CommentEntity comment = commentJpaRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));

        if (!comment.getUser().getEmail().equals(userEmail)) {
            throw new IllegalStateException("댓글 작성자만 삭제할 수 있습니다.");
        }

        commentJpaRepository.delete(comment);
    }

    //Entity -> DTO 변환
    private CommentResponseDto toDto(CommentEntity entity) {
        return CommentResponseDto.builder()
                .id(entity.getId())
                .content(entity.getContent())
                .authorEmail(entity.getUser().getEmail())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}