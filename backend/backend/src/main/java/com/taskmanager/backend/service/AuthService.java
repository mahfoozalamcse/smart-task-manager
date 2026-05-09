package com.taskmanager.backend.service;

import com.taskmanager.backend.dto.*;

public interface AuthService {

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}