package com.thanh.controller;

import com.thanh.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public String HomeControllerHandler() {
        ApiResponse apiResponse = new ApiResponse();

        return "apiResponse";
    }
}
