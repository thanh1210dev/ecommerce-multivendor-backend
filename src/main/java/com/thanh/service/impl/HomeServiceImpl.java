package com.thanh.service.impl;

import com.thanh.domain.HomeCategorySection;
import com.thanh.model.Deal;
import com.thanh.model.Home;
import com.thanh.model.HomeCategory;
import com.thanh.repository.DealRepository;
import com.thanh.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HomeServiceImpl implements HomeService {
    private final DealRepository dealRepository;

    @Override
    public Home createHomePageData(List<HomeCategory> allCategories) {
        List<HomeCategory> gridCaterories = allCategories.stream()
                .filter(category->
                        category.getSection() == HomeCategorySection.GRID)
                .collect(Collectors.toList());

        List<HomeCategory> shopByCategories = allCategories.stream()
                .filter(category->
                        category.getSection() == HomeCategorySection.SHOP_BY_CATEGORIES)
                .collect(Collectors.toList());

        List<HomeCategory> electricCaterories = allCategories.stream()
                .filter(category->
                        category.getSection() == HomeCategorySection.ELECTRIC_CATEGORIES)
                .collect(Collectors.toList());

        List<HomeCategory> dealCaterories = allCategories.stream()
                .filter(category->
                        category.getSection() == HomeCategorySection.DEALS)
                .collect(Collectors.toList());

        List<Deal> createdDeals = new ArrayList<>();

        if (dealRepository.findAll().isEmpty()){
            List<Deal> deals = allCategories.stream()
                    .filter(category->
                            category.getSection() == HomeCategorySection.DEALS)
                    .map(category-> new Deal(null,10,category))
                    .collect(Collectors.toList());
            createdDeals = dealRepository.saveAll(deals);

        }else createdDeals = dealRepository.findAll();

        Home home = new Home();
        home.setGrid(gridCaterories);
        home.setShopByCategories(shopByCategories);
        home.setElectricCategories(electricCaterories);
        home.setDeals(createdDeals);
        home.setDealCategories(dealCaterories);
        return home;
    }
}
