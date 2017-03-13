package com.example;

import java.util.List;

public interface CardRepository {

    List<Card> findAll();

    Card findOne();

    void save(Card card);

    Card findByName(String name);
}
