package com.orion.DigiWallet.controller;

import com.orion.DigiWallet.model.Transaction;
import com.orion.DigiWallet.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    // CREDIT MONEY API
    // TO TEST THIS API USE THE URL BELOW:
    // http://localhost:8080/api/transactions/create?walletId=1&categoryId=8&amount=1000
    @PostMapping("/create")
    public Transaction creditAmount(
            @RequestParam Long walletId,
            @RequestParam Long categoryId,
            @RequestParam Double amount) {

        return transactionService.createTransaction(walletId, categoryId, amount);
    }

    //TODO: 4.1
    // GET TRANSACTION BY ID API
    // -------------------------
    // http://localhost:8080/api/transactions/{id}
    // METHOD NAME: getTransactionById
    // RESPONSE BODY: Transaction JSON
    @GetMapping("/{id}")
    public Transaction getTransactionById(@PathVariable Long id) {
        return transactionService.getTransactionById(id);
    }

    //TODO: 4.2
    // GET TRANSACTIONS BY USER ID API
    // -------------------------
    // http://localhost:8080/api/transactions/user/{userId}
    // METHOD NAME: getTransactionsByUserId
    // RESPONSE BODY: List of Transaction JSONs
    @GetMapping("/user/{userId}")
    public List<Transaction> getTransactionsByUserId(@PathVariable Long userId) {
        return transactionService.getTransactionsByUserId(userId);
    }
}
