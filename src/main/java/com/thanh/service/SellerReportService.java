package com.thanh.service;

import com.thanh.model.Seller;
import com.thanh.model.SellerReport;

public interface SellerReportService {
    SellerReport getSellerReport(Seller seller);
    SellerReport updateSellerReport(SellerReport sellerReport);

}
