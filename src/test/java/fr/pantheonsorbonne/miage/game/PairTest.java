package fr.pantheonsorbonne.miage.game;

import fr.pantheonsorbonne.miage.utils.combinations.Pair;
import fr.pantheonsorbonne.miage.utils.Card;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Deque;
import java.util.PriorityQueue;

public class PairTest {

    Pair pair = new Pair();
    
    @Test
    void testIsContainedIn() {

        PriorityQueue<Card> handWithPair = new PriorityQueue<>();
        handWithPair.add(new Card(Card.Suit.CLUBS, Card.Rank.JACK));
        handWithPair.add(new Card(Card.Suit.DIAMONDS, Card.Rank.JACK));
        handWithPair.add(new Card(Card.Suit.HEARTS, Card.Rank.THREE));
        handWithPair.add(new Card(Card.Suit.HEARTS, Card.Rank.TWO));
        assertEquals(true, pair.isContainedIn(handWithPair));
    }

    @Test
    void testGetHighestPair() {
        PriorityQueue<Card> cardsWithHighestPair = new PriorityQueue<>();
        cardsWithHighestPair.add(new Card(Card.Suit.HEARTS, Card.Rank.ACE));
        cardsWithHighestPair.add(new Card(Card.Suit.DIAMONDS, Card.Rank.ACE));
        cardsWithHighestPair.add(new Card(Card.Suit.CLUBS, Card.Rank.KING));
        cardsWithHighestPair.add(new Card(Card.Suit.SPADES, Card.Rank.KING));


        Deque<Card> resultWithHighestPair = Pair.getHighestPair(cardsWithHighestPair);

        assertNotNull(resultWithHighestPair, "Should find the highest pair in the first set of cards");

        if (resultWithHighestPair != null) {
            assertEquals(2, resultWithHighestPair.size(), "Highest pair should have 2 cards");
            assertTrue(resultWithHighestPair.containsAll(cardsWithHighestPair), "Should contain all cards in the highest pair");
        }
    }
}