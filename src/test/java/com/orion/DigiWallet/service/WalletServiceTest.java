package com.orion.DigiWallet.service;

import com.orion.DigiWallet.model.User;
import com.orion.DigiWallet.model.Wallet;
import com.orion.DigiWallet.repository.WalletRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("WalletService Unit Tests")
class WalletServiceTest {

    @Mock
    private WalletRepository walletRepository;

    @InjectMocks
    private WalletService walletService;

    private Wallet wallet;

    // ---------------------------------------------------------
    // COMMON OBJECT SETUP ONLY
    // ---------------------------------------------------------
    @BeforeEach
    void setUp() {
        wallet = new Wallet();
        wallet.setId(1L);
    }

    // ---------------------------------------------------------
    @Nested
    @DisplayName("getWalletById()")
    class GetWalletByIdTests {

        @Test
        @DisplayName("Given existing wallet id when getWalletById then return wallet")
        void givenExistingWalletId_whenGetWalletById_thenReturnWallet() {

            // GIVEN
            Mockito.when(walletRepository.findById(1L))
                    .thenReturn(Optional.of(wallet));

            // WHEN
            Wallet result = walletService.getWalletById(1L);

            // THEN
            assertThat(result).isNotNull();
            assertThat(result.getId()).isEqualTo(1L);
        }

        @Test
        @DisplayName("Given non-existing wallet id when getWalletById then throw exception")
        void givenNonExistingWalletId_whenGetWalletById_thenThrowException() {

            // GIVEN
            Mockito.when(walletRepository.findById(1L))
                    .thenReturn(Optional.empty());

            // WHEN / THEN
            assertThatThrownBy(() ->
                    walletService.getWalletById(1L))
                    .isInstanceOf(RuntimeException.class)
                    .hasMessage("Wallet not found");
        }
    }

    // ---------------------------------------------------------
    @Nested
    @DisplayName("getWalletByUserId()")
    class GetWalletByUserIdTests {

        @Test
        @DisplayName("Given existing user id when getWalletByUserId then return wallet")
        void givenExistingUserId_whenGetWalletByUserId_thenReturnWallet() {
            // GIVEN
            User user = new User();
            user.setId(42L);
            wallet.setUser(user);

            Mockito.when(walletRepository.findByUserId(42L))
                    .thenReturn(Optional.of(wallet));

            // WHEN
            Wallet result = walletService.getWalletByUserId(42L);

            // THEN
            assertThat(result).isNotNull();
            assertThat(result.getUser()).isNotNull();
            assertThat(result.getUser().getId()).isEqualTo(42L);
        }

        @Test
        @DisplayName("Given non-existing user id when getWalletByUserId then throw exception")
        void givenNonExistingUserId_whenGetWalletByUserId_thenThrowException() {
            // GIVEN
            Mockito.when(walletRepository.findByUserId(99L))
                    .thenReturn(Optional.empty());

            // WHEN / THEN
            assertThatThrownBy(() -> walletService.getWalletByUserId(99L))
                    .isInstanceOf(RuntimeException.class)
                    .hasMessage("Wallet not found for user");
        }
    }

    // ---------------------------------------------------------
    @Nested
    @DisplayName("createWallet()")
    class CreateWalletTests {

        @Test
        @DisplayName("Given wallet without balance and status when createWallet then set defaults")
        void givenWalletWithoutBalanceAndStatus_whenCreateWallet_thenDefaultsAreApplied() {

            // GIVEN
            Wallet newWallet = new Wallet();
            newWallet.setId(2L);

            Mockito.when(walletRepository.save(Mockito.any(Wallet.class)))
                    .thenAnswer(invocation -> invocation.getArgument(0));

            // WHEN
            Wallet savedWallet = walletService.createWallet(newWallet);

            // THEN
            assertThat(savedWallet.getBalance())
                    .isEqualByComparingTo(BigDecimal.ZERO);
            assertThat(savedWallet.getStatus())
                    .isEqualTo("ACTIVE");

            Mockito.verify(walletRepository).save(newWallet);
        }

        @Test
        @DisplayName("Given wallet with balance and status when createWallet then keep values")
        void givenWalletWithBalanceAndStatus_whenCreateWallet_thenValuesRemainUnchanged() {

            // GIVEN
            Wallet newWallet = new Wallet();
            newWallet.setId(3L);
            newWallet.setBalance(BigDecimal.valueOf(5000));
            newWallet.setStatus("ACTIVE");

            Mockito.when(walletRepository.save(Mockito.any(Wallet.class)))
                    .thenAnswer(invocation -> invocation.getArgument(0));

            // WHEN
            Wallet savedWallet = walletService.createWallet(newWallet);

            // THEN
            assertThat(savedWallet.getBalance())
                    .isEqualByComparingTo("5000");
            assertThat(savedWallet.getStatus())
                    .isEqualTo("ACTIVE");
        }
    }
}
