package com.example.spring_security_blog_tutorial.services;

import com.example.spring_security_blog_tutorial.domain.PostStatus;
import com.example.spring_security_blog_tutorial.domain.dtos.CreatePostRequestDto;
import com.example.spring_security_blog_tutorial.domain.dtos.UpdatePostRequestDto;
import com.example.spring_security_blog_tutorial.domain.entites.Category;
import com.example.spring_security_blog_tutorial.domain.entites.Post;
import com.example.spring_security_blog_tutorial.domain.entites.Tag;
import com.example.spring_security_blog_tutorial.domain.entites.User;
import com.example.spring_security_blog_tutorial.repositories.PostRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;


@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;
    private CategoryService categoryService;
    private TagService tagService;

    public PostServiceImpl(PostRepository postRepository, CategoryService categoryService, TagService tagService) {
        this.postRepository = postRepository;
        this.categoryService = categoryService;
        this.tagService = tagService;
    }

    @Override
    @Transactional
    public List<Post> getPosts(UUID categoryId, UUID tagId) {
        if (categoryId != null && tagId != null) {
            Category category = categoryService.getCategoryById(categoryId);
            Tag tag = tagService.getTagById(tagId);
            return postRepository.findAllByStatusAndCategoryAndTagsContaining(
                    PostStatus.PUBLISHED,
                    category,
                    tag
            );
        } else if (categoryId != null) {
            Category category = categoryService.getCategoryById(categoryId);
            return postRepository.findAllByStatusAndCategory(PostStatus.PUBLISHED, category);
        } else if (tagId != null) {
            Tag tag = tagService.getTagById(tagId);
            return postRepository.findAllByStatusAndTagsContaining(PostStatus.PUBLISHED, tag);
        } else {
            return postRepository.findAllByStatus(PostStatus.PUBLISHED);
        }
    }

    @Override
    public List<Post> getDraftPosts(User user) {
       return postRepository.findAllByAuthorAndStatus(user,PostStatus.PUBLISHED);
    }

    @Override
    public Post createPost(User user, CreatePostRequestDto createPostRequestDto) {

        Post newPost = new Post();
        newPost.setTitle(createPostRequestDto.getTitle());
        newPost.setContent(createPostRequestDto.getContent());
        newPost.setStatus(createPostRequestDto.getStatus());
        newPost.setReadingTime(0);

        newPost.setAuthor(user);

        Category category = categoryService.getCategoryById(createPostRequestDto.getCategoryId());
        newPost.setCategory(category);

        Set<UUID> tagIds = createPostRequestDto.getTagIds();
        var savedTags = tagService.getTagByIds(tagIds);
        newPost.setTags(new HashSet<>(savedTags));

        return  postRepository.save(newPost);
    }

    @Override
    public Post updatePost(UUID id, UpdatePostRequestDto updatePostRequestDto) {
       var existingPost =  postRepository.findById(id)
                .orElseThrow();

        existingPost.setTitle(updatePostRequestDto.getTitle());

        existingPost.setContent(updatePostRequestDto.getContent());

        existingPost.setStatus(updatePostRequestDto.getStatus());

        existingPost.setReadingTime(0);

        Category category = categoryService.getCategoryById(updatePostRequestDto.getCategoryId());
        existingPost.setCategory(category);

        Set<UUID> tagIds = updatePostRequestDto.getTagIds();
        var savedTags = tagService.getTagByIds(tagIds);
        existingPost.setTags(new HashSet<>(savedTags));

       return postRepository.save(existingPost);
    }
}
