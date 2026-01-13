package com.example.spring_security_blog_tutorial.services;

import com.example.spring_security_blog_tutorial.repositories.UserRepository;
import com.example.spring_security_blog_tutorial.domain.dtos.BlogUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class BlogUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public BlogUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       var user =  this.userRepository.findByEmail(email)
               .orElseThrow();

       return new BlogUserDetails(user);
    }
}
