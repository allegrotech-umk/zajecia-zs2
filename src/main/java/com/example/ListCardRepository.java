package com.example;

import com.google.common.collect.Lists;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
public class ListCardRepository implements CardRepository {

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

    @Override
    public List<Card> findAll() {
        return cards;
    }

    @Override
    public Card findOne() {
        Random rand = new Random();
        return cards.get(rand.nextInt(cards.size()));
    }

    @Override
    public void save(final Card card) {
        cards.add(card);
    }

    @Override
    public Card findByName(final String name) {
        for (Card card : cards) {
            if (card.getWord().equals(name)) {
                return card;
            }
        }
        throw new RuntimeException("Nie znaleziono karty!!");
    }

    @Override
    public void remove(final Card card) {
        cards.remove(card);
    }
}
