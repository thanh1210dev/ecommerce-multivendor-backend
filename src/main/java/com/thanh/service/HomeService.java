package com.thanh.service;

import com.thanh.model.Home;
import com.thanh.model.HomeCategory;

import java.util.List;

public interface HomeService {
    Home createHomePageData(List<HomeCategory> allCategories);
}
