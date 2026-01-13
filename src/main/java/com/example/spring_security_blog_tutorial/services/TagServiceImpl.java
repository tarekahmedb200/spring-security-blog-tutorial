package com.example.spring_security_blog_tutorial.services;

import com.example.spring_security_blog_tutorial.domain.entites.Tag;
import com.example.spring_security_blog_tutorial.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class TagServiceImpl implements TagService {

    private TagRepository tagRepository;

    @Autowired
    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public List<Tag> getTags() {
        return  this.tagRepository.findAllWithPostCount();
    }

    @Override
    public List<Tag> createTags(Set<String> tagNames) {

        var tagsToAdd = tagNames
                .stream()
                .map(
                        (tagName) ->
                        Tag.builder()
                                .name(tagName)
                                .build()
                )
                .toList();

        return  this.tagRepository.saveAll(tagsToAdd);
    }

    @Override
    public Tag getTagById(UUID id) {
        return  tagRepository.findById(id)
                .orElseThrow();
    }

    @Override
    public List<Tag> getTagByIds(Set<UUID> ids) {
        return  tagRepository.findAllById(ids);
    }
}
