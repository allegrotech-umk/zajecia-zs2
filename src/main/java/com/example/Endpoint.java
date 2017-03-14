package com.example;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Endpoint {

    private CardService cardService;

    public Endpoint(final CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/get-card")
    public Card getCard() {
        return cardService.findOne();
    }

    @GetMapping("/get-cards")
    public List<Card> getCards() {
        return cardService.findAll();
    }

    @PostMapping("/add-card")
    @ResponseStatus(HttpStatus.OK)
    public void addCard(@RequestBody Card newCard) {
        cardService.save(newCard);
    }

    @GetMapping("/find-card")
    public Card findCard(@RequestParam String word) {
        return cardService.findByName(word);
    }

    @DeleteMapping("/remove-card")
    @ResponseStatus(HttpStatus.OK)
    public void removeCard(@RequestBody Card card) {
        cardService.remove(card);
    }
}
