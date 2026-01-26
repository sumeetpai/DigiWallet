package com.orion.DigiWallet.service;

import com.orion.DigiWallet.model.Card;
import com.orion.DigiWallet.repository.CardRepository;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    private final CardRepository cardRepository;

    // Constructor Injection (Best Practice)
    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    // -----------------------------------------
    // CREATE CARD
    // -----------------------------------------
    public Card createCard(Card card) {

        // STEP 1: Check if card number already exists
        if (cardRepository.existsByCardNumber(card.getCardNumber())) {
            throw new RuntimeException("Card number already exists");
        }

        // STEP 2: Save and return the card
        return cardRepository.save(card);
    }

    // -----------------------------------------
    // GET CARD BY ID
    // -----------------------------------------
    public Card getCardById(Long id) {

        // STEP 1: Fetch card by ID
        return cardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Card not found with id: " + id));
    }

    // -----------------------------------------
    // UPDATE CARD
    // -----------------------------------------
    public Card updateCard(Long id, Card updatedCard) {

        // STEP 1: Fetch existing card
        Card existingCard = cardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Card not found with id: " + id));

        // STEP 2: Update allowed fields
        existingCard.setCardType(updatedCard.getCardType());
        existingCard.setStatus(updatedCard.getStatus());
        existingCard.setExpiryDate(updatedCard.getExpiryDate());

        // STEP 3: Save updated card
        return cardRepository.save(existingCard);
    }

    // -----------------------------------------
    // DELETE CARD
    // -----------------------------------------
    public void deleteCard(Long id) {

        // STEP 1: Check if card exists
        if (!cardRepository.existsById(id)) {
            throw new RuntimeException("Card not found with id: " + id);
        }

        // STEP 2: Delete card
        cardRepository.deleteById(id);
    }
}
