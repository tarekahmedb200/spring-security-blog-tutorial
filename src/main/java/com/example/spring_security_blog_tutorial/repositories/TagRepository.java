package com.example.spring_security_blog_tutorial.repositories;

import com.example.spring_security_blog_tutorial.domain.entites.Tag;
import com.example.spring_security_blog_tutorial.domain.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TagRepository   extends JpaRepository<Tag, UUID> {
}
