package com.thanh.controller;

import com.thanh.model.Seller;
import com.thanh.model.Transaction;
import com.thanh.service.SellerService;
import com.thanh.service.TransactionService;
import com.thanh.service.impl.SellerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;
    private final SellerService sellerService;


    @GetMapping("/sellers")
    public ResponseEntity<List<Transaction>> getTransactionsBySeller(
            @RequestHeader("Authorization") String jwt
    ) throws Exception{
        Seller seller= sellerService.getSellerProfile(jwt);

        List<Transaction> transactions = transactionService.getAllTransactionsBySeller(seller);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions(){
        List<Transaction> transactions = transactionService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }
}
