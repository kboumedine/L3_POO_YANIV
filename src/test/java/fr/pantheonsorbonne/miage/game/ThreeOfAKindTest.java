package fr.pantheonsorbonne.miage.game;


import fr.pantheonsorbonne.miage.utils.combinations.ThreeOfAKind;
import fr.pantheonsorbonne.miage.utils.Card;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Deque;
import java.util.PriorityQueue;

public class ThreeOfAKindTest{

    ThreeOfAKind threeOfAKind = new ThreeOfAKind();
    
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
        assertEquals(true, threeOfAKind.isContainedIn(handWithThreeOfAKind));
    }

    @Test
    void testGetThreeOfAKind() {
        PriorityQueue<Card> cardsWithThreeOfAKind = new PriorityQueue<>();
        cardsWithThreeOfAKind.add(new Card(Card.Suit.HEARTS, Card.Rank.ACE));
        cardsWithThreeOfAKind.add(new Card(Card.Suit.DIAMONDS, Card.Rank.ACE));
        cardsWithThreeOfAKind.add(new Card(Card.Suit.CLUBS, Card.Rank.ACE));
        cardsWithThreeOfAKind.add(new Card(Card.Suit.SPADES, Card.Rank.KING));

        PriorityQueue<Card> cardsWithoutThreeOfAKind = new PriorityQueue<>();
        cardsWithoutThreeOfAKind.add(new Card(Card.Suit.HEARTS, Card.Rank.ACE));
        cardsWithoutThreeOfAKind.add(new Card(Card.Suit.DIAMONDS, Card.Rank.KING));
        cardsWithoutThreeOfAKind.add(new Card(Card.Suit.CLUBS, Card.Rank.QUEEN));
        cardsWithoutThreeOfAKind.add(new Card(Card.Suit.SPADES, Card.Rank.TEN));

        Deque<Card> resultWithThreeOfAKind = ThreeOfAKind.getThreeOfAKind(cardsWithThreeOfAKind);
        Deque<Card> resultWithoutThreeOfAKind = ThreeOfAKind.getThreeOfAKind(cardsWithoutThreeOfAKind);

        assertNotNull(resultWithThreeOfAKind, "Should find three of a kind in the first set of cards");
        assertNull(resultWithoutThreeOfAKind, "Should not find three of a kind in the second set of cards");

        if (resultWithThreeOfAKind != null) {
            assertEquals(3, resultWithThreeOfAKind.size(), "Three of a kind should have 3 cards");
            assertTrue(resultWithThreeOfAKind.containsAll(cardsWithThreeOfAKind), "Should contain all cards in the three of a kind");
        }
    }


}


