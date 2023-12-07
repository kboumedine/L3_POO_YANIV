package fr.pantheonsorbonne.miage.game;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
