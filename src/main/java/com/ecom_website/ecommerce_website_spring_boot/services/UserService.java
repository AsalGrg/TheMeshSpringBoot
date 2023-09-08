package com.ecom_website.ecommerce_website_spring_boot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom_website.ecommerce_website_spring_boot.entities.User;
import com.ecom_website.ecommerce_website_spring_boot.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {
        User savedUser = this.userRepository.save(user);
        return savedUser;
    }

    public User authenticateUser(String email, String password) {
        User validUser = this.userRepository.getUserRegisterDetails(email, password);
        return validUser;
    }

    public User getUserById(int id) {
        User user = this.userRepository.findById(id).get();
        return user;
    }
}
