package com.thanh.service;

import com.thanh.model.User;

public interface UserService {
    User findUserByJwtToken(String jwtToken) throws Exception;
    User findUserByEmail(String email) throws Exception;
}
