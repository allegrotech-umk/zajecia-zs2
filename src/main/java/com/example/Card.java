package com.example;

import java.util.List;

public class Card {

    private String word;

    private List<String> forbiddenWord;

    public Card() {
    }

    public Card(String word, List<String> forbiddenWord) {
        this.word = word;
        this.forbiddenWord = forbiddenWord;
    }

    public String getWord() {
        return word;
    }

    public List<String> getForbiddenWord() {
        return forbiddenWord;
    }
}
