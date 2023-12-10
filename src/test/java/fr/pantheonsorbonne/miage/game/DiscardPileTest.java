package fr.pantheonsorbonne.miage.game;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Deque;
import java.util.LinkedList;

import fr.pantheonsorbonne.miage.utils.Card;
import fr.pantheonsorbonne.miage.utils.DiscardPile;

public class DiscardPileTest {

    @Test
    void testInitialization() {
        DiscardPile discardPile = new DiscardPile();
        assertTrue(discardPile.getDiscardPile().isEmpty(), "Discard pile should be empty initially");
    }

    @Test
    void testAddAll() {
        DiscardPile discardPile = new DiscardPile();
        Deque<Card> cardsToDiscard = new LinkedList<>();
        cardsToDiscard.add(new Card(Card.Suit.HEARTS, Card.Rank.ACE));
        cardsToDiscard.add(new Card(Card.Suit.DIAMONDS, Card.Rank.TWO));

        discardPile.addAll(cardsToDiscard);

        assertEquals(2, discardPile.getDiscardPile().size(), "Discard pile should have two cards after adding");
        assertTrue(discardPile.getDiscardPile().containsAll(cardsToDiscard), "Discard pile should contain the added cards");
    }

    @Test
    void testClearDiscardPile() {
        DiscardPile discardPile = new DiscardPile();
        discardPile.getDiscardPile().add(new Card(Card.Suit.DIAMONDS, Card.Rank.JACK));
        discardPile.getDiscardPile().add(new Card(Card.Suit.SPADES, Card.Rank.TEN));

        discardPile.clearDiscardPile();

        assertTrue(discardPile.getDiscardPile().isEmpty(), "Discard pile should be empty after clearing");
    }

    // Add more tests as needed to cover other methods and edge cases.
}

