package com.example;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
public class CardServiceTest {

    private CardRepository listCardRepository;

    private CardService cardService;

    @Before
    public void before() throws Exception {
        listCardRepository = mock(ListCardRepository.class);
        cardService = new CardService(listCardRepository);
    }

    @Test
    public void testFindAllWhenListOfCardsIsEmpty() throws Exception {
        // given
        given(listCardRepository.findAll()).willReturn(Collections.emptyList());

        // when
        final List<Card> cards = cardService.findAll();

        // then
        assertThat(cards).isEqualTo(Collections.emptyList());
    }

    @Test
    public void testFindAllWhenListOfCardsHasMoreElements() throws Exception {
        // given
        final Card firstCard = new Card("foo", Collections.singletonList("baz"));
        final Card secondCard = new Card("bar", Collections.singletonList("baz"));
        given(listCardRepository.findAll()).willReturn(Arrays.asList(firstCard, secondCard));

        // when
        final List<Card> cards = cardService.findAll();

        // then
        assertThat(cards.size()).isEqualTo(2);
        assertThat(cards).isEqualTo(Arrays.asList(secondCard, firstCard));
    }

    @Test
    public void testFindByNameWhenElementExists() throws Exception {
        // given
        final Card card = new Card("foo", Collections.singletonList("bar"));
        given(listCardRepository.findByName("foo")).willReturn(card);

        // when
        final Card foundCard = cardService.findByName("foo");

        // then
        assertThat(foundCard).isEqualTo(card);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testFindByNameWhenCardDoesNotExists() throws Exception {
        // when
        when(listCardRepository.findByName("foo")).thenThrow(RuntimeException.class);

        // then
        assertThatThrownBy(() -> cardService.findByName("foo"))
                .isInstanceOf(RuntimeException.class)
                .withFailMessage("Nie znaleziono karty!!");
    }
}
