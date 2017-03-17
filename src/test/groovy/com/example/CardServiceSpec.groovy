package com.example

import spock.lang.Specification

class CardServiceSpec extends Specification {

    CardRepository cardRepository

    CardService cardService

    def setup() {
        cardRepository = Stub(ListCardRepository)
        cardService = new CardService(cardRepository)
    }

    def "should return empty list of cards"() {
        given:
        cardRepository.findAll() >> { [] }

        when:
        def cards = cardService.findAll()

        then:
        cards.size() == 0
    }

    def "should return list with many elements"() {
        given:
        def firstCard = new Card("foo", Collections.singletonList("baz"))
        def secondCard = new Card("bar", Collections.singletonList("baz"))
        cardRepository.findAll() >> { [firstCard, secondCard] }

        when:
        def cards = cardService.findAll()

        then:
        cards == [secondCard, firstCard]
    }

    def "should return one card when find by name"() {
        given:
        def card = new Card("foo", Collections.singletonList("bar"))
        cardRepository.findByName("foo") >> { card }

        when:
        def foundCard = cardService.findByName("foo")

        then:
        foundCard == card
    }

    def "should throw exception when card does not exists"() {
        given:
        cardRepository.findByName("foo") >> {
            throw new RuntimeException("Nie znaleziono karty!!")
        }

        when:
        cardService.findByName("foo")

        then:
        RuntimeException exception = thrown()
        exception.message == "Nie znaleziono karty!!"
    }
}
