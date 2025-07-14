package com.example.newwave1str.repository;

import com.example.newwave1str.web.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentJpaRepository extends JpaRepository<CommentEntity, Long> {

    // 특정 게시글(postId)에 해당하는 댓글 목록 조회
    List<CommentEntity> findByPostId(Long postId);

    // (선택) 작성자 이메일로 댓글 조회
    List<CommentEntity> findByUserEmail(String email);

}