package com.example.spring_security_blog_tutorial.controllers;

import com.example.spring_security_blog_tutorial.domain.dtos.CreateTagsRequest;
import com.example.spring_security_blog_tutorial.domain.dtos.TagResponse;
import com.example.spring_security_blog_tutorial.mapper.TagMapper;
import com.example.spring_security_blog_tutorial.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tags")
public class TagController {

    private TagService tagService;
    private TagMapper tagMapper;

    @Autowired
    public TagController(TagService tagService, TagMapper tagMapper) {
        this.tagService = tagService;
        this.tagMapper = tagMapper;
    }

    @GetMapping
    public ResponseEntity<List<TagResponse>> getAllTags() {

      var tags = this.tagService.getTags();
      var tagResponses =  tags.stream().map(tagMapper::toTagResponse)
              .toList();
      return ResponseEntity.ok(tagResponses);
    }

    @PostMapping
    public ResponseEntity<List<TagResponse>> createTags(
            @RequestBody CreateTagsRequest createTagsRequest
            ) {
        var tags = tagService.createTags(createTagsRequest.getNames());

        var tagResponses =  tags.stream()
                .map(tagMapper::toTagResponse)
                .toList();

        return ResponseEntity.ok(tagResponses);
    }

}
