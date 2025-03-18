package com.thanh.controller;

import com.thanh.domain.USER_ROLE;
import com.thanh.model.User;
import com.thanh.model.VerificationCode;
import com.thanh.repository.UserRepository;
import com.thanh.request.LoginOtpRequest;
import com.thanh.request.LoginRequest;
import com.thanh.response.ApiResponse;
import com.thanh.response.AuthResponse;
import com.thanh.response.SignUpRequest;
import com.thanh.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserRepository userRepository;
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler (@RequestBody SignUpRequest req) throws Exception {
        String jwt = authService.createUser(req);
        AuthResponse res = new AuthResponse();
        res.setJwt(jwt);
        res.setMessage("register success");
        res.setRole(USER_ROLE.ROLE_CUSTOMER);
        return ResponseEntity.ok(res);
    }

//    @PostMapping("/signup")
//    public ResponseEntity<User> createUserHandler (@RequestBody SignUpRequest req) throws Exception {
//        User user = new User();
//        user.setEmail(req.getEmail());
//        user.setFullName(req.getFullName());
//        User savedUser = userRepository.save(user);
//        return ResponseEntity.ok(savedUser);
//    }

    @PostMapping("/sent/login-signup-otp")
    public ResponseEntity<ApiResponse> sentOtpHandler (
            @RequestBody LoginOtpRequest req) throws Exception {
        authService.sentLoginOtp(req.getEmail(),req.getRole());

        ApiResponse res = new ApiResponse();

        res.setMessage("otp sent successfully");
        return ResponseEntity.ok(res);
    }

    @PostMapping("/signing")
    public ResponseEntity<AuthResponse> loginHandler (
            @RequestBody LoginRequest req) throws Exception {
        AuthResponse authResponse = authService.signing(req);

        return ResponseEntity.ok(authResponse);
    }
}
