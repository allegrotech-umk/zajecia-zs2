package com.example;

import com.google.common.collect.Lists;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class Endpoint {


    List<Card> cards = Lists.newArrayList();

    @GetMapping("/get-card")
    public Card getCard() {
        return new Card("Szkoła", Arrays.asList("Uczelnia", "Nauczyciel"));
    }

    @GetMapping("/get-cards")
    public List<Card> getCards() {
        return cards;
    }

    @PostMapping("/add-card")
    public ResponseEntity addCard(@RequestBody Card newCard) {
        cards.add(newCard);
        return new ResponseEntity(HttpStatus.OK);
    }
}
