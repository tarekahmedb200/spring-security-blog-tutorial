package com.example.spring_security_blog_tutorial.mapper;

import com.example.spring_security_blog_tutorial.domain.PostStatus;
import com.example.spring_security_blog_tutorial.domain.dtos.CategoryDto;
import com.example.spring_security_blog_tutorial.domain.dtos.CreateCategoryRequest;
import com.example.spring_security_blog_tutorial.domain.entites.Category;
import com.example.spring_security_blog_tutorial.domain.entites.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Locale;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE )
public interface CategoryMapper {

      @Mapping(target = "postCount",source = "posts",qualifiedByName = "calculatePostCount")
      CategoryDto toDto(Category category);

      Category toEntity(CreateCategoryRequest createCategoryRequest);

      @Named("calculatePostCount")
    default long calculatePostCount(List<Post> posts) {
        return posts == null ? 0 :
                posts.stream().filter(post -> PostStatus.PUBLISHED.equals(post.getStatus()))
                .count();
    }
}
