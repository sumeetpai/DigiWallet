package com.orion.DigiWallet.service;

import com.orion.DigiWallet.model.Wallet;
import com.orion.DigiWallet.repository.WalletRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class WalletService {

    private static final Logger logger =
            LoggerFactory.getLogger(WalletService.class);

    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public Wallet getWalletById(Long id) {
        logger.info("Fetching wallet with id {}", id);
        return walletRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Wallet not found"));
    }

    // New: Fetch wallet by user id
    public Wallet getWalletByUserId(Long userId) {
        logger.info("Fetching wallet for user id {}", userId);
        return walletRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Wallet not found for user"));
    }

    public Wallet createWallet(Wallet wallet) {
        logger.info("Creating new wallet");

        // Set default balance if not provided
        if (wallet.getBalance() == null) {
            wallet.setBalance(BigDecimal.ZERO);
        }

        // Set default status if not provided
        if (wallet.getStatus() == null) {
            wallet.setStatus("ACTIVE");
        }

        return walletRepository.save(wallet);
    }
}
