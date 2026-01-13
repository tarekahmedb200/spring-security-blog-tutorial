package com.example.spring_security_blog_tutorial.services;


import com.example.spring_security_blog_tutorial.domain.entites.User;

import java.util.UUID;

public interface UserService {
    User getUserById(UUID id);
}
