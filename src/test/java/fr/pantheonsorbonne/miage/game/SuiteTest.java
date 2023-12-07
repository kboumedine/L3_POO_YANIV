package fr.pantheonsorbonne.miage.game;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Deque;
import java.util.PriorityQueue;

import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.utils.Card;
import fr.pantheonsorbonne.miage.utils.combinations.Suite;

public class SuiteTest {
    
    Suite suit = new Suite();
    
    @Test
    void testIsContainedIn() {

        PriorityQueue<Card> handWithSuit = new PriorityQueue<>();
        handWithSuit.add(new Card(Card.Suit.HEARTS, Card.Rank.THREE));
        handWithSuit.add(new Card(Card.Suit.DIAMONDS, Card.Rank.JACK));
        handWithSuit.add(new Card(Card.Suit.HEARTS, Card.Rank.TWO));
        handWithSuit.add(new Card(Card.Suit.HEARTS, Card.Rank.FOUR));
        assertEquals(true, suit.isContainedIn(handWithSuit));
    }

    @Test
    void testGetHighestSequence(){
        PriorityQueue<Card> handWithSuit = new PriorityQueue<>();
        handWithSuit.add(new Card(Card.Suit.HEARTS, Card.Rank.THREE));
        handWithSuit.add(new Card(Card.Suit.DIAMONDS, Card.Rank.JACK));
        handWithSuit.add(new Card(Card.Suit.HEARTS, Card.Rank.TWO));
        handWithSuit.add(new Card(Card.Suit.HEARTS, Card.Rank.FOUR));
        handWithSuit.add(new Card(Card.Suit.HEARTS, Card.Rank.FIVE));
        handWithSuit.add(new Card(Card.Suit.DIAMONDS, Card.Rank.JACK));
        handWithSuit.add(new Card(Card.Suit.HEARTS, Card.Rank.SIX));
        handWithSuit.add(new Card(Card.Suit.HEARTS, Card.Rank.SEVEN));
        Deque<Card> highestSequence = Suite.getHighestSuit(handWithSuit);

        // La suite de plus grande valeur pour HEARTS dans le test est [FIVE, SIX, SEVEN]
        // Comparer avec la suite renvoyée par la méthode
        assertEquals(3, highestSequence.size());
        assertEquals(Card.Rank.FIVE, highestSequence.getFirst().getRank());
        assertEquals(Card.Rank.SEVEN, highestSequence.getLast().getRank());
        assertEquals(Card.Suit.HEARTS, highestSequence.getFirst().getSuit());
        assertEquals(Card.Suit.HEARTS, highestSequence.getLast().getSuit());
    }


}
