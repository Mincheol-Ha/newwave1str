package com.example.newwave1str.repository;

import com.example.newwave1str.web.entity.PostEntity;
import com.example.newwave1str.web.entity.PostLikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostLikeJpaRepository extends JpaRepository<PostLikeEntity, Long> {
    Optional<PostLikeEntity> findByPostAndEmail(PostEntity post, String email);

    void delete(PostLikeEntity postLikeEntity);
}
