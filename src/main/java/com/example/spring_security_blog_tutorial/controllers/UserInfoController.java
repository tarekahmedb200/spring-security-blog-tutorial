package com.example.spring_security_blog_tutorial.controllers;

import com.example.spring_security_blog_tutorial.domain.entites.User;
import com.example.spring_security_blog_tutorial.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserInfoController {

    private UserRepository userRepository;

    @Autowired
    public UserInfoController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    List<User> getUsers() {
        return  userRepository.findAll();
    }
}
