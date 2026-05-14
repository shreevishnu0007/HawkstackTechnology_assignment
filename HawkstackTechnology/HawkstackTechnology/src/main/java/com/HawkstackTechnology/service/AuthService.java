package com.HawkstackTechnology.service;

import com.HawkstackTechnology.dto.LoginRequest;
import com.HawkstackTechnology.dto.RegisterRequest;

public interface AuthService {

    String register(RegisterRequest request);

    String login(LoginRequest request);
}
