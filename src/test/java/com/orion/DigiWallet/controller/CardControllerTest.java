package com.orion.DigiWallet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orion.DigiWallet.model.Card;
import com.orion.DigiWallet.model.Wallet;
import com.orion.DigiWallet.service.CardService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CardController.class)
@DisplayName("CardController Unit Tests")
class CardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CardService cardService;

    @Autowired
    private ObjectMapper objectMapper;

    // ---------------------------------------------------------------------
    @Nested
    @DisplayName("POST /api/cards/create")
    class CreateCardTests {

        @Test
        @DisplayName("Given valid card with wallet when createCard then return created card")
        void givenValidCardWithWallet_whenCreateCard_thenReturnCreatedCard() throws Exception {

            // GIVEN
            Wallet wallet = new Wallet();
            wallet.setId(10L);

            Card card = new Card();
            card.setId(1L);
            card.setWallet(wallet);
            card.setCardNumber("1234567812345678");
            card.setCardType("DEBIT");
            card.setStatus("ACTIVE");
            card.setExpiryDate(LocalDate.of(2030, 12, 31));
            card.setIssuedAt(LocalDateTime.now());

            Mockito.when(cardService.createCard(Mockito.any(Card.class)))
                    .thenReturn(card);

            // WHEN & THEN
            mockMvc.perform(post("/api/cards/create")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(card)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(1L))
                    .andExpect(jsonPath("$.cardNumber").value("1234567812345678"))
                    .andExpect(jsonPath("$.wallet.id").value(10L))
                    .andExpect(jsonPath("$.status").value("ACTIVE"));
        }
    }

    // ---------------------------------------------------------------------
    @Nested
    @DisplayName("GET /api/cards/{id}")
    class GetCardByIdTests {

        @Test
        @DisplayName("Given existing card id when getCardById then return card")
        void givenExistingCardId_whenGetCardById_thenReturnCard() throws Exception {

            // GIVEN
            Wallet wallet = new Wallet();
            wallet.setId(20L);

            Card card = new Card();
            card.setId(2L);
            card.setWallet(wallet);
            card.setCardNumber("9999888877776666");
            card.setStatus("ACTIVE");

            Mockito.when(cardService.getCardById(2L))
                    .thenReturn(card);

            // WHEN & THEN
            mockMvc.perform(get("/api/cards/{id}", 2L))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(2L))
                    .andExpect(jsonPath("$.wallet.id").value(20L))
                    .andExpect(jsonPath("$.cardNumber").value("9999888877776666"));
        }
    }

    // ---------------------------------------------------------------------
    @Nested
    @DisplayName("PUT /api/cards/{id}")
    class UpdateCardTests {

        @Test
        @DisplayName("Given valid update request when updateCard then return updated card")
        void givenValidUpdate_whenUpdateCard_thenReturnUpdatedCard() throws Exception {

            // GIVEN
            Wallet wallet = new Wallet();
            wallet.setId(30L);

            Card updatedCard = new Card();
            updatedCard.setId(3L);
            updatedCard.setWallet(wallet);
            updatedCard.setCardNumber("4444333322221111");
            updatedCard.setStatus("BLOCKED");

            Mockito.when(cardService.updateCard(Mockito.eq(3L), Mockito.any(Card.class)))
                    .thenReturn(updatedCard);

            // WHEN & THEN
            mockMvc.perform(put("/api/cards/{id}", 3L)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(updatedCard)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.status").value("BLOCKED"))
                    .andExpect(jsonPath("$.wallet.id").value(30L));
        }
    }

    // ---------------------------------------------------------------------
    @Nested
    @DisplayName("DELETE /api/cards/{id}")
    class DeleteCardTests {

        @Test
        @DisplayName("Given card id when deleteCard then return success message")
        void givenCardId_whenDeleteCard_thenReturnSuccessMessage() throws Exception {

            // GIVEN
            Mockito.doNothing().when(cardService).deleteCard(4L);

            // WHEN & THEN
            mockMvc.perform(delete("/api/cards/{id}", 4L))
                    .andExpect(status().isOk())
                    .andExpect(content().string("Card deleted successfully"));
        }
    }
}
