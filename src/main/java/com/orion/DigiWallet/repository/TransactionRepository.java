package com.orion.DigiWallet.repository;

import com.orion.DigiWallet.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByWallet_User_Id(Long userId);
}

