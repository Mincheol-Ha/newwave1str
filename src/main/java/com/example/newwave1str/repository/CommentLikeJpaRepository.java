package com.example.newwave1str.repository;

import com.example.newwave1str.web.entity.CommentEntity;
import com.example.newwave1str.web.entity.CommentLikeEntity;
import com.example.newwave1str.web.entity.PostEntity;
import com.example.newwave1str.web.entity.PostLikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentLikeJpaRepository extends JpaRepository<CommentLikeEntity, Long> {
    Optional<CommentLikeEntity> findByCommentAndEmail(CommentEntity comment, String email);
}
