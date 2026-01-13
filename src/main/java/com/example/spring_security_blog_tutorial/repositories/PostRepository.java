package com.example.spring_security_blog_tutorial.repositories;

import com.example.spring_security_blog_tutorial.domain.PostStatus;
import com.example.spring_security_blog_tutorial.domain.entites.Category;
import com.example.spring_security_blog_tutorial.domain.entites.Post;
import com.example.spring_security_blog_tutorial.domain.entites.Tag;
import com.example.spring_security_blog_tutorial.domain.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {

    // ✅ Find all posts by status
    List<Post> findAllByStatus(PostStatus status);

    // ✅ Find all posts by category
    List<Post> findAllByCategory(Category category);

    // ✅ Find all posts that contain a specific tag
    List<Post> findAllByTagsContaining(Tag tag);

    // ✅ Find all posts by status and category
    List<Post> findAllByStatusAndCategory(PostStatus status, Category category);

    // ✅ Find all posts by status and tag
    List<Post> findAllByStatusAndTagsContaining(PostStatus status, Tag tag);

    // ✅ Find all posts by category and tag
    List<Post> findAllByCategoryAndTagsContaining(Category category, Tag tag);

    // ✅ Find all posts by status, category, and tag
    List<Post> findAllByStatusAndCategoryAndTagsContaining(PostStatus status,
                                                           Category category,
                                                           Tag tag);

    List<Post> findAllByAuthorAndStatus(User user,PostStatus status);
}

