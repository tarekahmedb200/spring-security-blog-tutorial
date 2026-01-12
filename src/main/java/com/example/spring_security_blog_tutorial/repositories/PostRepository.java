package com.example.spring_security_blog_tutorial.repositories;

import com.example.spring_security_blog_tutorial.domain.entites.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {
}
