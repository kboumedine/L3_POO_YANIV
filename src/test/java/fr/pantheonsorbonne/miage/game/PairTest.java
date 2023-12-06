package fr.pantheonsorbonne.miage.game;

import fr.pantheonsorbonne.miage.utils.combinations.Pair;
import fr.pantheonsorbonne.miage.utils.Card;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}