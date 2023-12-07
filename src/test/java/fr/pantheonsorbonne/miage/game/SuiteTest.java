package fr.pantheonsorbonne.miage.game;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayDeque;
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
    void testIsContainedIn2() {

        PriorityQueue<Card> handWithNoSuit = new PriorityQueue<>();
        handWithNoSuit.add(new Card(Card.Suit.HEARTS, Card.Rank.THREE));
        handWithNoSuit.add(new Card(Card.Suit.DIAMONDS, Card.Rank.JACK));
        handWithNoSuit.add(new Card(Card.Suit.HEARTS, Card.Rank.TWO));
        handWithNoSuit.add(new Card(Card.Suit.SPADES, Card.Rank.FOUR));
        assertEquals(false, suit.isContainedIn(handWithNoSuit));
    }

    @Test
    void testHasTenJackQueenSequence() {

        Deque<Card> handWithTheSequence = new ArrayDeque<>();
        handWithTheSequence.add(new Card(Card.Suit.HEARTS, Card.Rank.JACK));
        handWithTheSequence.add(new Card(Card.Suit.DIAMONDS, Card.Rank.TWO));
        handWithTheSequence.add(new Card(Card.Suit.HEARTS, Card.Rank.TEN));
        handWithTheSequence.add(new Card(Card.Suit.HEARTS, Card.Rank.QUEEN));
        assertEquals(true, suit.hasTenJackQueenSequence(handWithTheSequence));
    }

    


}
