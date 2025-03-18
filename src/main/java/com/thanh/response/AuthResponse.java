package com.thanh.response;

import com.thanh.domain.USER_ROLE;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class AuthResponse {
    private String jwt;
    private String message;
    private USER_ROLE role;
}
