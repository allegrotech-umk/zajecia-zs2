package com.example

import org.springframework.http.HttpStatus
import spock.lang.Unroll

class EndpointSpec extends IntegrationSpec {

    def "should return list of cards with three elements"() {
        when:
        def response = restTemplate.getForEntity(localUrl("/get-cards"), Card[])

        then:
        response.statusCode == HttpStatus.OK
        response.body.size() == 3
    }

    def "should return card with name 'Szkoła'"() {
        when:
        def response = restTemplate.getForEntity(localUrl("/find-card?word=Szkoła"), Card)

        then:
        response.statusCode == HttpStatus.OK
        with(response.body) {
            word == "Szkoła"
            forbiddenWord == ["Uczelnia",
                              "Nauczyciel",
                              "Gimnazjum",
                              "Podstawówka",
                              "Uczeń"]
        }
    }

    def "should throw exception when card with name 'Foo' does not exists"() {
        when:
        def response = restTemplate.getForEntity(localUrl("/find-card?word=Foo"), Card)

        then:
        response.statusCode == HttpStatus.INTERNAL_SERVER_ERROR
    }

    @Unroll
    def "should return card with #name"(name, statusCode, cardWord, cardForbiddenWord) {
        when:
        def response = restTemplate.getForEntity(localUrl("/find-card?word=${name}"), Card)

        then:
        response.statusCode == statusCode
        with(response.body) {
            word == cardWord
            forbiddenWord == cardForbiddenWord
        }

        where:
        //@formatter:off
        name     || statusCode                       | cardWord | cardForbiddenWord
        "Szkoła" || HttpStatus.OK                    | "Szkoła" | ["Uczelnia", "Nauczyciel", "Gimnazjum", "Podstawówka", "Uczeń"]
        "Foo"    || HttpStatus.INTERNAL_SERVER_ERROR | null     | null
        //@formatter:on
    }
}
