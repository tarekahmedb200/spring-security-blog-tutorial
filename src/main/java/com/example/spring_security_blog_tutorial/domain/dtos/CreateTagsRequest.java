package com.example.spring_security_blog_tutorial.domain.dtos;


import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateTagsRequest {
    @NotEmpty(message = "At least one tag is req")
    private Set<String> names;
}
