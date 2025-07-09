package com.example.newwave1str.repository;

import com.example.newwave1str.web.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostJpaRepository extends JpaRepository<PostEntity, Long> {
    List<PostEntity> findByAuthorEmail(String authorEmail);
}