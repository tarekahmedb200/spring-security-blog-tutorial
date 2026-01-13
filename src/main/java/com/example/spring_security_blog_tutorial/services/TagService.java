package com.example.spring_security_blog_tutorial.services;
import com.example.spring_security_blog_tutorial.domain.entites.Tag;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface TagService {
    List<Tag> getTags();
    List<Tag> createTags(Set<String> tagNames);
    Tag getTagById(UUID id);
    List<Tag> getTagByIds(Set<UUID> ids);
}
