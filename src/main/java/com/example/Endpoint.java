package com.example;

import com.google.common.collect.Lists;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class Endpoint {

    @GetMapping("/get-card")
    public Card getCard() {
        return cards.get(0);
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
