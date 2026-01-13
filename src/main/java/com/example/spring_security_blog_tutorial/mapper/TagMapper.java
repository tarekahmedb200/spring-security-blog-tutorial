package com.example.spring_security_blog_tutorial.mapper;

import com.example.spring_security_blog_tutorial.domain.PostStatus;
import com.example.spring_security_blog_tutorial.domain.dtos.TagResponse;
import com.example.spring_security_blog_tutorial.domain.entites.Post;
import com.example.spring_security_blog_tutorial.domain.entites.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TagMapper {

    @Mapping(target = "postCount",source = "posts",qualifiedByName = "calculatePostCount")
    TagResponse toTagResponse(Tag tag);

    @Named("calculatePostCount")
    default Integer calculatePostCount(Set<Post> posts) {
        return posts == null ? 0 :
                (int)posts.stream().filter(post -> PostStatus.PUBLISHED.equals(post.getStatus()))
                        .count();
    }
}
