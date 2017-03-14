package com.example;

import java.util.List;
import java.util.Objects;

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

    @Override
    public int hashCode() {
        return Objects.hash(word);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Card card = (Card) o;
        return Objects.equals(word, card.word);
    }
}
