package com.example.spring_security_blog_tutorial.services;

import com.example.spring_security_blog_tutorial.domain.dtos.CreatePostRequestDto;
import com.example.spring_security_blog_tutorial.domain.dtos.UpdatePostRequestDto;
import com.example.spring_security_blog_tutorial.domain.entites.Post;
import com.example.spring_security_blog_tutorial.domain.entites.User;

import java.util.List;
import java.util.UUID;

public interface PostService {
    List<Post> getPosts(UUID categoryId,UUID tagId);
    List<Post> getDraftPosts(User user);
    Post createPost(User user, CreatePostRequestDto createPostRequestDto);
    Post updatePost(UUID id, UpdatePostRequestDto updatePostRequestDto);
}
