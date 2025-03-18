package com.thanh.service;

import com.thanh.domain.USER_ROLE;
import com.thanh.request.LoginRequest;
import com.thanh.response.AuthResponse;
import com.thanh.response.SignUpRequest;

public interface AuthService {

    void sentLoginOtp(String email, USER_ROLE role) throws Exception;

    String createUser(SignUpRequest req) throws Exception;
    AuthResponse signing(LoginRequest req) throws Exception;
}
