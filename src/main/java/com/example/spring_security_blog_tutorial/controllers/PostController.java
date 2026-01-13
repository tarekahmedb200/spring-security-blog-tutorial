package com.example.spring_security_blog_tutorial.controllers;


import com.example.spring_security_blog_tutorial.domain.dtos.CreatePostRequestDto;
import com.example.spring_security_blog_tutorial.domain.dtos.PostDto;
import com.example.spring_security_blog_tutorial.domain.dtos.UpdatePostRequestDto;
import com.example.spring_security_blog_tutorial.mapper.PostMapper;
import com.example.spring_security_blog_tutorial.services.PostService;
import com.example.spring_security_blog_tutorial.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    private final PostService postService;
    private final PostMapper postMapper;
    private final UserService userService;


    @Autowired
    public PostController(PostService postService, PostMapper postMapper, UserService userService) {
        this.postService = postService;
        this.postMapper = postMapper;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts (
               @RequestParam(required = false)UUID categoryId ,
               @RequestParam(required = false)UUID tagId
       ) {
        var  postDtos =  postService.getPosts(categoryId,tagId)
                .stream()
                .map(postMapper::toDto)
                .toList();
        return  ResponseEntity.ok(postDtos);
       }

    @GetMapping("/drafts")
    public ResponseEntity<List<PostDto>> getdrafts (
            @RequestParam(required = false)UUID userId) {

        var user = userService.getUserById(userId);
        var draftPosts = postService.getDraftPosts(user)
                .stream()
                .map(postMapper::toDto)
                .toList();

        return  ResponseEntity.ok(draftPosts);
    }

    @PostMapping("/create")
    public ResponseEntity<PostDto> create (
            @RequestBody CreatePostRequestDto createPostRequestDto,
            @RequestParam(required = false)UUID userId
            ) {

        var user = userService.getUserById(userId);
        var post = postService.createPost(user,createPostRequestDto);

        return  ResponseEntity.ok(
             postMapper.toDto(post)
        );
    }

    @PutMapping("/update")
    public ResponseEntity<PostDto> update (
            @RequestParam(required = false)UUID id,
            @RequestBody UpdatePostRequestDto updatePostRequestDto
    ) {

        var post = postService.updatePost(id,updatePostRequestDto);

        return  ResponseEntity.ok(
                postMapper.toDto(post)
        );
    }

}
