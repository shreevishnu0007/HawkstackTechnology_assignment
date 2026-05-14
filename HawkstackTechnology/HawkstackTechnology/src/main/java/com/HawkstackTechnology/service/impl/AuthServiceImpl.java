package com.HawkstackTechnology.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HawkstackTechnology.dto.LoginRequest;
import com.HawkstackTechnology.dto.RegisterRequest;
import com.HawkstackTechnology.entity.User;
import com.HawkstackTechnology.repository.UserRepository;
import com.HawkstackTechnology.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public String register(RegisterRequest request) {

        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());

        if (existingUser.isPresent()) {
            return "User already exists!";
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        userRepository.save(user);

        return "User registered successfully!";
    }

    @Override
    public String login(LoginRequest request) {

        Optional<User> user = userRepository.findByEmail(request.getEmail());

        if (user.isEmpty()) {
            return "User not found!";
        }

        if (!user.get().getPassword().equals(request.getPassword())) {
            return "Invalid password!";
        }

        return "Login successful!";
    }
}