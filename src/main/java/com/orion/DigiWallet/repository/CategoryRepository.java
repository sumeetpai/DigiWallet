package com.orion.DigiWallet.repository;

import com.orion.DigiWallet.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}

