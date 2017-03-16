package com.example

import spock.lang.Specification

class CardServiceSpec extends Specification {

    def cardRepository = Mock(ListCardRepository)

    def cardService = new CardService(cardRepository)

    def "should return empty list of cards"() {
        when:
        def cards = cardService.findAll()

        then:
        1 * cardRepository.findAll() >> { [] }
        cards.size() == 0
    }

    def "should return list with one element"() {
        given:
        def card = new Card("foo", Collections.singletonList("bar"))

        when:
        def cards = cardService.findAll()

        then:
        1 * cardRepository.findAll() >> { [card] }
        cards.size() == 1
        cards.get(0) == card
    }

    def "should return list with many elements"() {
        given:
        def firstCard = new Card("foo", Collections.singletonList("baz"))
        def secondCard = new Card("bar", Collections.singletonList("baz"))

        when:
        def cards = cardService.findAll()

        then:
        1 * cardRepository.findAll() >> { [firstCard, secondCard] }
        cards.size() == 2
        cards.get(0) == secondCard
    }

    def "should save card"() {
        given:
        def card = new Card("foo", Collections.singletonList("bar"))

        when:
        cardService.save(card)

        then:
        1 * cardRepository.save(card)
    }

    def "should return one card"() {
        given:
        def card = new Card("foo", Collections.singletonList("bar"))

        when:
        cardService.findOne()

        then:
        1 * cardRepository.findOne() >> { card }
    }

    def "should return one card when find by name"() {
        given:
        def card = new Card("foo", Collections.singletonList("bar"))

        when:
        cardService.findByName("foo")

        then:
        1 * cardRepository.findByName("foo") >> { card }
    }

    def "should throw exception when card does not exists"() {
        when:
        cardService.findByName("foo")

        then:
        1 * cardRepository.findByName("foo") >> {
            throw new RuntimeException("Nie znaleziono karty!!")
        }
        RuntimeException exception = thrown()
        exception.message == "Nie znaleziono karty!!"
    }

    def "should remove card"() {
        given:
        def card = new Card("foo", Collections.singletonList("bar"))

        when:
        cardService.remove(card)

        then:
        1 * cardRepository.remove(card)
    }
}
