package com.orion.DigiWallet.controller;

import com.orion.DigiWallet.model.Wallet;
import com.orion.DigiWallet.service.WalletService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wallets")
public class WalletController {

    private static final Logger logger =
            LoggerFactory.getLogger(WalletController.class);

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    // GET WALLET BY ID
    @GetMapping("/{id}")
    public Wallet getWalletById(@PathVariable Long id) {
        logger.info("GET /api/wallets/{} called", id);
        return walletService.getWalletById(id);
    }

    // GET WALLET BY USER ID
    // Example: GET /api/wallets/user/8
    @GetMapping("/user/{userId}")
    public Wallet getWalletByUserId(@PathVariable Long userId) {
        logger.info("GET /api/wallets/user/{} called", userId);
        return walletService.getWalletByUserId(userId);
    }

    //TODO 4.4
    // CREATE WALLET API
    // -------------------------
    // http://localhost:8080/api/wallets
    // {
    //  "user": { "id": 8 },
    //  "balance": 5000,
    //  "currency": "INR"
    // }
    // POST METHOD
    // REQUEST BODY: Wallet JSON
    // RESPONSE BODY: Created Wallet JSON
    @PostMapping
    public Wallet createWallet(@RequestBody Wallet wallet) {
        logger.info("POST /api/wallets called");
        return walletService.createWallet(wallet);
    }
}
