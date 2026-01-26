package com.orion.DigiWallet.repository;
import com.orion.DigiWallet.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
    // Find a wallet by the id of the associated user
    Optional<Wallet> findByUserId(Long userId);
}
