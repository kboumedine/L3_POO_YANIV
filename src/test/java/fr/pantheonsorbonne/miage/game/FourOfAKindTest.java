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

        PriorityQueue<Card> handWithThreeOfAKind = new PriorityQueue<>();
        handWithThreeOfAKind.add(new Card(Card.Suit.CLUBS, Card.Rank.JACK));
        handWithThreeOfAKind.add(new Card(Card.Suit.HEARTS, Card.Rank.JACK));
        handWithThreeOfAKind.add(new Card(Card.Suit.DIAMONDS, Card.Rank.JACK));
        handWithThreeOfAKind.add(new Card(Card.Suit.HEARTS, Card.Rank.FIVE));
        handWithThreeOfAKind.add(new Card(Card.Suit.HEARTS, Card.Rank.FOUR));
        handWithThreeOfAKind.add(new Card(Card.Suit.HEARTS, Card.Rank.THREE));
        handWithThreeOfAKind.add(new Card(Card.Suit.HEARTS, Card.Rank.TWO));
        assertEquals(true, fourOfAKind.isContainedIn(handWithThreeOfAKind));
    }
}