package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class CardService {

    private static final Logger logger = LoggerFactory.getLogger(CardService.class);

    private final CardRepository cardRepository;

    public CardService(final CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public List<Card> findAll() {

        logger.info("pobieram liste kard");

        List<Card> cards = cardRepository.findAll();

        cards.sort(new Comparator<Card>() {
            @Override
            public int compare(final Card o1, final Card o2) {
                return o1.getWord().compareTo(o2.getWord());
            }
        });

        //        Collections.sort((ArrayList)cards, Ordering.natural());

        return cards;
    }

    public void save(Card save) {

        logger.info("dodaje karte");

        cardRepository.save(save);
    }

    public Card findOne() {

        logger.info("pobieram losowo wybrana karte");

        return cardRepository.findOne();
    }

    public Card findByName(String name) {

        logger.info("pobieram karte o nazwie {}", name);

        return cardRepository.findByName(name);
    }
}
