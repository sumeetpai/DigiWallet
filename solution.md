1.1
logger.info("Fetching user with id {}", id);
        return userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User not found with id: " + id)
                );


1.2
String greeting = "";
if ("ADMIN".equalsIgnoreCase(role)) {
greeting += "Admin access enabled";
} else {
greeting += "User access";
}
return greeting;


1.7
@Test
void generateGreetingMsg_shouldReturnAdminMessage_whenRoleIsAdmin() {
String result = service.generateGreetingMsg("ADMIN");
assertEquals("Admin access enabled", result);
}

        @Test
        void generateGreetingMsg_shouldReturnAdminMessage_whenRoleIsAdminInLowerCase() {
            String result = userService.generateGreetingMsg("admin");
            assertEquals("Admin access enabled", result);
        }

        @Test
        void generateGreetingMsg_shouldReturnUserMessage_whenRoleIsUser() {
            String result = userService.generateGreetingMsg("USER");
            assertEquals("User access", result);
        }

        @Test
        void generateGreetingMsg_shouldReturnUserMessage_whenRoleIsNull() {
            String result = userService.generateGreetingMsg(null);
            assertEquals("User access", result);
        }



1.9

// GIVEN
Long userId = 1L;
when(mockUserRepository.findById(userId)).thenReturn(user1);

        // WHEN
        User result = userService.getUserById(userId);

        // THEN
        assertNotNull(result);
        assertEquals(userId, result.getId());



1.10

@Nested
class CreateUserTests {

        @Test
        void createUser_shouldSaveUser_whenUsernameDoesNotExist() {

            // GIVEN
            User inputUser = new User();
            inputUser.setUsername("newuser");

            User savedUser = new User();
            savedUser.setId(1L);
            savedUser.setUsername("newuser");

            when(userRepository.existsByUsername("newuser"))
                    .thenReturn(false);

            when(userRepository.save(any(User.class)))
                    .thenReturn(savedUser);

            // WHEN
            User result = userService.createUser(inputUser);

            // THEN
            assertNotNull(result);
            assertEquals(1L, result.getId());

            verify(userRepository).existsByUsername("newuser");
            verify(userRepository).save(any(User.class));
        }

        @Test
        void createUser_shouldThrowException_whenUsernameExists() {

            // GIVEN
            User inputUser = new User();
            inputUser.setUsername("existinguser");

            when(userRepository.existsByUsername("existinguser"))
                    .thenReturn(true);

            // WHEN + THEN
            assertThrows(RuntimeException.class,
                    () -> userService.createUser(inputUser));

            verify(userRepository).existsByUsername("existinguser");
            verify(userRepository, never()).save(any(User.class));
        }
    }


todo 2.1


@Entity
@Table(name = "card")
//
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Each card belongs to one wallet
    @ManyToOne
    @JoinColumn(name = "wallet_id", nullable = false)
    private Wallet wallet;

    // Physical card details
    @Column(nullable = false, unique = true, length = 16)
    private String cardNumber;

    @Column(nullable = false, length = 10)
    private String cardType;
    // DEBIT / PREPAID / VIRTUAL

    private LocalDate expiryDate;

    @Column(nullable = false, length = 20)
    private String status;
    // ACTIVE / BLOCKED / EXPIRED


    // -----------------------------------------
    // Getters & Setters
    // -----------------------------------------
    public Long getId() {
        return id;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardType() {
        return cardType;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public String getStatus() {
        return status;
    }


    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}




3.1
3.2

Optional<Card> findByCardNumber(String cardNumber);
boolean existsByCardNumber(String cardNumber);



4.1,4.2,4.3
----------------


package com.orion.DigiWallet.repository;

import com.orion.DigiWallet.model.Card;
import com.orion.DigiWallet.model.Wallet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CardRepositoryTest {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private WalletRepository walletRepository;

    // -----------------------------------------
    // TEST 1: Save Card
    // -----------------------------------------
    @Test
    void shouldSaveCardSuccessfully() {
        // GIVEN
        Wallet wallet = new Wallet();
        wallet.setBalance(5000.00);
        wallet.setCurrency("INR");
        wallet.setStatus("ACTIVE");
        Wallet savedWallet = walletRepository.save(wallet);

        Card card = new Card();
        card.setWallet(savedWallet);
        card.setCardNumber("1111222233334444");
        card.setCardType("DEBIT");
        card.setStatus("ACTIVE");
        card.setExpiryDate(LocalDate.now().plusYears(3));
        card.setIssuedAt(LocalDateTime.now());

        // WHEN
        Card savedCard = cardRepository.save(card);

        // THEN
        assertThat(savedCard.getId()).isNotNull();
        assertThat(savedCard.getCardNumber()).isEqualTo("1111222233334444");
    }

    // -----------------------------------------
    // TEST 2: Find by Card Number
    // -----------------------------------------
    @Test
    void shouldFindCardByCardNumber() {
        // GIVEN
        Wallet wallet = new Wallet();
        wallet.setBalance(3000.00);
        wallet.setCurrency("INR");
        wallet.setStatus("ACTIVE");
        Wallet savedWallet = walletRepository.save(wallet);

        Card card = new Card();
        card.setWallet(savedWallet);
        card.setCardNumber("5555666677778888");
        card.setCardType("DEBIT");
        card.setStatus("ACTIVE");
        card.setExpiryDate(LocalDate.now().plusYears(2));
        card.setIssuedAt(LocalDateTime.now());
        cardRepository.save(card);

        // WHEN
        Optional<Card> fetchedCard =
                cardRepository.findByCardNumber("5555666677778888");

        // THEN
        assertThat(fetchedCard).isPresent();
        assertThat(fetchedCard.get().getCardNumber())
                .isEqualTo("5555666677778888");
    }

    // -----------------------------------------
    // TEST 3: Exists by Card Number
    // -----------------------------------------
    @Test
    void shouldCheckIfCardExistsByCardNumber() {
        // GIVEN
        Wallet wallet = new Wallet();
        wallet.setBalance(2000.00);
        wallet.setCurrency("INR");
        wallet.setStatus("ACTIVE");
        Wallet savedWallet = walletRepository.save(wallet);

        Card card = new Card();
        card.setWallet(savedWallet);
        card.setCardNumber("9999000011112222");
        card.setCardType("PREPAID");
        card.setStatus("ACTIVE");
        card.setExpiryDate(LocalDate.now().plusYears(1));
        card.setIssuedAt(LocalDateTime.now());
        cardRepository.save(card);

        // WHEN & THEN
        assertThat(cardRepository.existsByCardNumber("9999000011112222"))
                .isTrue();

        assertThat(cardRepository.existsByCardNumber("0000111122223333"))
                .isFalse();
    }

    // -----------------------------------------
    // TEST 4: Domain Method - isExpired()
    // -----------------------------------------
    @Test
    void shouldCheckIfCardIsExpired() {
        // GIVEN
        Card expiredCard = new Card();
        expiredCard.setExpiryDate(LocalDate.now().minusDays(1));

        Card validCard = new Card();
        validCard.setExpiryDate(LocalDate.now().plusYears(1));

        // THEN
        assertThat(expiredCard.isExpired()).isTrue();
        assertThat(validCard.isExpired()).isFalse();
    }
}
