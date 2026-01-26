package com.orion.DigiWallet.repository;

import com.orion.DigiWallet.model.Category;
import com.orion.DigiWallet.model.Transaction;
import com.orion.DigiWallet.model.User;
import com.orion.DigiWallet.model.Wallet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
// FIRST SEE THE APPLICATION.PROPERTIES IN TEST RESOURCES FOLDER
// ALSO LOOK AT THE DBSCIPT.SQL AND DATAINSERT.SQL FILES IN MAIN FOLDER
//RUN THE SHELL SCRIPT TO CREATE THE TABLES IN TEST DATABASE BEFORE RUNNING THE TESTS
public class TransactionRepositoryTest {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private User user;
    private Wallet wallet;
    private Category category;

    @BeforeEach
    void setUp() {
        // GIVEN
        // Create and persist User
        User u = new User();
        u.setUsername("Test User");
        u.setEmail("txn.user@test.com");
        this.user = userRepository.save(u);

        // Create and persist Wallet linked to User
        Wallet w = new Wallet();
        w.setUser(user);
        w.setBalance(BigDecimal.valueOf(1000));
        w.setCurrency("INR");
        w.setStatus("ACTIVE");
        this.wallet = walletRepository.save(w);

        // Create and persist Category
        Category c = new Category();

        c.setType("EXPENSE");
        this.category = categoryRepository.save(c);
    }

    // TODO 5.1
    // Write a test to verify:
    // - Transaction can be saved successfully
    // - Transaction ID is generated
    @Test
    void shouldSaveTransactionSuccessfully() {
        // GIVEN
        Transaction transaction = new Transaction();
        transaction.setWallet(wallet);
        transaction.setCategory(category);
        transaction.setAmount(250.0);
        transaction.setTransactionType("DEBIT");
        transaction.setReferenceId("TXN-TEST-001");

        // WHEN
        Transaction savedTransaction = transactionRepository.save(transaction);

        // THEN
        assertThat(savedTransaction.getId()).isNotNull();
    }

    // TODO 5.2
    // Write a test to verify:
    // - Transactions can be fetched by User ID
    @Test
    void shouldFindTransactionsByUserId() {
        // GIVEN
        Transaction t1 = new Transaction();
        t1.setWallet(wallet);
        t1.setCategory(category);
        t1.setAmount(100.0);
        t1.setTransactionType("DEBIT");
        t1.setReferenceId("TXN-TEST-002");

        Transaction t2 = new Transaction();
        t2.setWallet(wallet);
        t2.setCategory(category);
        t2.setAmount(200.0);
        t2.setTransactionType("DEBIT");
        t2.setReferenceId("TXN-TEST-003");

        transactionRepository.save(t1);
        transactionRepository.save(t2);

        // WHEN
        List<Transaction> transactions =
                transactionRepository.findByWallet_User_Id(user.getId());

        // THEN
        assertThat(transactions).hasSize(2);
    }

    // TODO 5.3
    // Write a test to verify:
    // - Empty list is returned when no transactions exist for user
    @Test
    void shouldReturnEmptyListWhenNoTransactionsForUser() {
        // GIVEN
        Long nonExistingUserId = 9999L;

        // WHEN
        List<Transaction> transactions =
                transactionRepository.findByWallet_User_Id(nonExistingUserId);

        // THEN
        assertThat(transactions).isEmpty();
    }
}
