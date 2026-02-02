package com.orion.DigiWallet.controller;

import com.orion.DigiWallet.model.Card;
import com.orion.DigiWallet.service.CardService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

// 4.4.1 Review card controller API
// Review using Swagger UI
// Also test using unit testing (check testing class if exists)

@RestController
@RequestMapping("/api/cards")
public class CardController {

    // Inject CardService using constructor injection
    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    // 4.4.2 Return 201 CREATED
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Card createCard(@RequestBody Card card) {
        return cardService.createCard(card);
    }

    // 4.4.3 Return 200 OK
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Card getCardById(@PathVariable Long id) {
        return cardService.getCardById(id);
    }

    // 4.4.4 Return 200 OK
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Card updateCard(
            @PathVariable Long id,
            @RequestBody Card updatedCard) {

        return cardService.updateCard(id, updatedCard);
    }

    // 4.4.5 Return 200 OK
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteCard(@PathVariable Long id) {
        cardService.deleteCard(id);
        return "Card deleted successfully";
    }
}
