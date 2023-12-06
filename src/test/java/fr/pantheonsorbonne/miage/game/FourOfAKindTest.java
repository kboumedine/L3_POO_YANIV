package fr.pantheonsorbonne.miage.game;


import fr.pantheonsorbonne.miage.utils.combinations.FourOfAKind;
import fr.pantheonsorbonne.miage.utils.Card;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.PriorityQueue;

public class FourOfAKindTest {

    FourOfAKind fourOfAKind = new FourOfAKind();
    
    @Test
    void testIsContainedIn() {

        PriorityQueue<Card> handWithFourOfAKind = new PriorityQueue<>();
        handWithFourOfAKind.add(new Card(Card.Suit.CLUBS, Card.Rank.JACK));
        handWithFourOfAKind.add(new Card(Card.Suit.CLUBS, Card.Rank.JACK));
        handWithFourOfAKind.add(new Card(Card.Suit.DIAMONDS, Card.Rank.JACK));
        handWithFourOfAKind.add(new Card(Card.Suit.HEARTS, Card.Rank.JACK));
        handWithFourOfAKind.add(new Card(Card.Suit.HEARTS, Card.Rank.THREE));
        handWithFourOfAKind.add(new Card(Card.Suit.HEARTS, Card.Rank.THREE));
        handWithFourOfAKind.add(new Card(Card.Suit.HEARTS, Card.Rank.TWO));
        assertEquals(true, fourOfAKind.isContainedIn(handWithFourOfAKind));
    }
}