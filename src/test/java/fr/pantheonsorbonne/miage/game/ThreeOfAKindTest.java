package fr.pantheonsorbonne.miage.game;


import fr.pantheonsorbonne.miage.utils.combinations.ThreeOfAKind;
import fr.pantheonsorbonne.miage.utils.Card;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.PriorityQueue;

public abstract class ThreeOfAKindTest{

    @Test
    void testIsContainedIn() {
        ThreeOfAKind threeOfAKind = new ThreeOfAKind();

        // Test when hand contains Three of a Kind
        PriorityQueue<Card> handWithThreeOfAKind = new PriorityQueue<>();
        handWithThreeOfAKind.add(new Card(Card.Suit.CLUBS, Card.Rank.JACK));
        handWithThreeOfAKind.add(new Card(Card.Suit.HEARTS, Card.Rank.JACK));
        handWithThreeOfAKind.add(new Card(Card.Suit.DIAMONDS, Card.Rank.JACK));
        handWithThreeOfAKind.add(new Card(Card.Suit.HEARTS, Card.Rank.FIVE));
        handWithThreeOfAKind.add(new Card(Card.Suit.HEARTS, Card.Rank.FOUR));
        handWithThreeOfAKind.add(new Card(Card.Suit.HEARTS, Card.Rank.THREE));
        handWithThreeOfAKind.add(new Card(Card.Suit.HEARTS, Card.Rank.TWO));
        assertEquals(true, threeOfAKind.isContainedIn(handWithThreeOfAKind));

    }

}


