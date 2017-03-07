package com.example;

import java.util.List;

public class Card {

    public String word;
    public List<String> forbiddenWord;

    public Card() {
    }

    public Card(String word, List<String> forbiddenWord) {
        this.word = word;
        this.forbiddenWord = forbiddenWord;
    }
}
