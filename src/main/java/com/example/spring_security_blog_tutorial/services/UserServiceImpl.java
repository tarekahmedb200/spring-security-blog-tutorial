package com.example.spring_security_blog_tutorial.services;

import com.example.spring_security_blog_tutorial.domain.entites.User;
import com.example.spring_security_blog_tutorial.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserById(UUID id) {
        return  this.userRepository.findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException("user not found")
                );
    }
}
