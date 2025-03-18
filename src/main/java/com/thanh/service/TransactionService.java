package com.thanh.service;

import com.thanh.model.Order;
import com.thanh.model.Seller;
import com.thanh.model.Transaction;

import java.util.List;

public interface TransactionService {
    Transaction createTransaction(Order order);

    List<Transaction> getAllTransactionsBySeller(Seller seller);

    List<Transaction> getAllTransactions();
}
