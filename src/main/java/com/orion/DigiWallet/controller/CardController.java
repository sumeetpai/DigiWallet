package com.orion.DigiWallet.controller;

import com.orion.DigiWallet.model.Card;
import com.orion.DigiWallet.service.CardService;
import org.springframework.web.bind.annotation.*;

//TODO: 5.1
//Make this class a REST controller to handle CRUD operations for Card entity
//Use appropriate annotations
@RestController
@RequestMapping("/api/cards")
public class CardController {

    //TODO: 5.2
    // private cardservice variable here
    // Inject CardService using constructor injection
    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    //TODO: 5.3
    // Write a method to handle POST request to create a new card
    // URL: /api/cards/create
    // Method Name: createCard
    // Request Body: Card JSON
    // Response Body: Created Card JSON
    // call createCard method from CardService
    // for this you need to
    @PostMapping("/create")
    public Card createCard(@RequestBody Card card) {
        return cardService.createCard(card);
    }

    //TODO: 5.4
    // Write a method to handle GET request to fetch a card by its ID
    // URL: /api/cards/{id}
    // Method Name: getCardById
    // Response Body: Card JSON
    // call getCardById method from CardService
    @GetMapping("/{id}")
    public Card getCardById(@PathVariable Long id) {
        return cardService.getCardById(id);
    }

    //TODO: 5.5
    // Write a method to handle PUT request to update an existing card
    // URL: /api/cards/{id}
    // Method Name: updateCard
    // Request Body: Updated Card JSON
    // Response Body: Updated Card JSON
    // call updateCard method from CardService
    @PutMapping("/{id}")
    public Card updateCard(
            @PathVariable Long id,
            @RequestBody Card updatedCard) {

        return cardService.updateCard(id, updatedCard);
    }

    //TODO: 5.6
    // Write a method to handle DELETE request to delete a card by its ID
    // URL: /api/cards/{id}
    // Method Name: deleteCard
    // Response Body: Success message or status
    // call deleteCard method from CardService
    @DeleteMapping("/{id}")
    public String deleteCard(@PathVariable Long id) {
        cardService.deleteCard(id);
        return "Card deleted successfully";
    }
}
