package com.thanh.controller;

import com.thanh.model.Home;
import com.thanh.model.HomeCategory;
import com.thanh.service.HomeCategoryService;
import com.thanh.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor

public class HomeCategoryController {
    private final HomeCategoryService homeCategoryService;
    private final HomeService homeService;
    @PostMapping("/home/categories")
    public ResponseEntity<Home> createHomeCategories(
            @RequestBody List<HomeCategory> homeCategories) {
        List<HomeCategory> categories = homeCategoryService.createCategories(homeCategories);
        Home home = homeService.createHomePageData(categories);
        return  new ResponseEntity<>(home, HttpStatus.ACCEPTED);
    }

    @GetMapping("/admin/home-category")
    public ResponseEntity<List<HomeCategory>> getHomeCategory()
    throws Exception {
        List<HomeCategory> categories = homeCategoryService.getAllHomeCategories();
        return  ResponseEntity.ok(categories);
    }

    @PatchMapping("/home-category/{id}")
    public ResponseEntity<HomeCategory> updateHomeCategory(
            @PathVariable Long id,
            @RequestBody HomeCategory homeCategory
    )throws Exception {
        HomeCategory updatedCategory = homeCategoryService.updateHomeCategory(homeCategory, id);
        return  ResponseEntity.ok(updatedCategory);
    }

}
