package com.example;

import com.google.common.collect.Lists;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@RestController
public class Endpoint {

    @GetMapping("/get-card")
    public Card getCard() {
        Random rand = new Random();
        return cards.get(rand.nextInt(cards.size()));
    }

    @GetMapping("/get-cards")
    public List<Card> getCards() {
        return cards;
    }

    @PostMapping("/add-card")
    @ResponseStatus(HttpStatus.OK)
    public void addCard(@RequestBody Card newCard) {
        cards.add(newCard);
    }

    @GetMapping("/find-card")
    public Card findCard(@RequestParam String word) {
        for(Card card : cards) {
            if (card.word.equals(word)) {
                return card;
            }
        }
        throw new RuntimeException("Nie znaleziono karty!!");
    }

    private List<Card> cards = Lists.newArrayList(
            new Card("Szkoła",
                    Arrays.asList("Uczelnia",
                            "Nauczyciel",
                            "Gimnazjum",
                            "Podstawówka",
                            "Uczeń")),
            new Card("Chińskie Jedzenie",
                    Arrays.asList("Ostre",
                            "Gorące",
                            "Smaczne",
                            "Chiny",
                            "Posiłek")),
            new Card("Ciepło",
                    Arrays.asList("Przyjaciele",
                            "Globalne",
                            "Podgrzewać",
                            "Rodzice",
                            "Słońce"))
    );

}
