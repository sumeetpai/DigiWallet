package com.orion.DigiWallet.repository;

import com.orion.DigiWallet.model.Card;
import com.orion.DigiWallet.model.User;
import com.orion.DigiWallet.model.Wallet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
// FIRST SEE THE APPLICATION.PROPERTIES IN TEST RESOURCES FOLDER
// ALSO LOOK AT THE DBSCIPT.SQL AND DATAINSERT.SQL FILES IN MAIN FOLDER
//RUN THE SHELL SCRIPT TO CREATE THE TABLES IN TEST DATABASE BEFORE RUNNING THE TESTS
public class CardRepositoryTest {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private UserRepository userRepository;

    private Wallet wallet;

    @BeforeEach
    void setUp() {
        // GIVEN
        // Create and save a User (required for Wallet)
        User user = new User();
        user.setUsername("Test User");
        user.setEmail("test.user@example.com");
        user = userRepository.save(user);

        // Create and save a Wallet linked to the user
        Wallet w = new Wallet();
        w.setUser(user);
        w.setBalance(BigDecimal.ZERO);
        w.setCurrency("INR");
        w.setStatus("ACTIVE");

        this.wallet = walletRepository.save(w);
    }

    // TODO 4.1
    // Write a test to verify:
    // - A Card can be saved successfully
    // - Card ID is generated
    @Test
    void shouldSaveCardSuccessfully() {
        // GIVEN
        Card card = new Card();
        card.setCardNumber("4111111111111111");
        card.setWallet(wallet);

        // WHEN
        Card savedCard = cardRepository.save(card);

        // THEN
        assertThat(savedCard.getId()).isNotNull();
    }

    // TODO 4.2
    // Write a test to verify:
    // - Card can be fetched by cardNumber
    @Test
    void shouldFindCardByCardNumber() {
        // GIVEN
        Card card = new Card();
        card.setCardNumber("5555444433332222");
        card.setWallet(wallet);
        cardRepository.save(card);

        // WHEN
        var result = cardRepository.findByCardNumber("5555444433332222");

        // THEN
        assertThat(result).isPresent();
    }

    // TODO 4.3
    // Write a test to verify:
    // - existsByCardNumber returns true for existing card
    // - existsByCardNumber returns false for non-existing card
    @Test
    void shouldCheckIfCardExistsByCardNumber() {
        // GIVEN
        Card card = new Card();
        card.setCardNumber("9999888877776666");
        card.setWallet(wallet);
        cardRepository.save(card);

        // WHEN + THEN
        assertThat(cardRepository.existsByCardNumber("9999888877776666")).isTrue();
        assertThat(cardRepository.existsByCardNumber("0000111122223333")).isFalse();
    }
}
