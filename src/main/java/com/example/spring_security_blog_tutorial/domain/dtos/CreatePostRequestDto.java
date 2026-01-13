package com.example.spring_security_blog_tutorial.domain.dtos;


import com.example.spring_security_blog_tutorial.domain.PostStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreatePostRequestDto {
    private String title;
    private String content;
    private UUID categoryId;
    private Set<UUID> tagIds = new HashSet<>();
    private PostStatus status;
}
