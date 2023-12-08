package fr.pantheonsorbonne.miage.game;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Deque;
import java.util.LinkedList;

import fr.pantheonsorbonne.miage.utils.Deck;
import fr.pantheonsorbonne.miage.utils.DiscardPile;
import fr.pantheonsorbonne.miage.utils.Card;

public class DeckTest {

    @Test
    void testInitializeDeck() {
        Deck deck = new Deck();
        assertEquals(52, deck.getDeck().size(), "Deck should be initialized with 52 cards");
    }

    @Test
    void testShuffleDeck() {
        Deck deck = new Deck();
        Deque<Card> originalDeck = new LinkedList<>(deck.getDeck());

        deck.shuffleDeck();

        assertNotEquals(originalDeck, deck.getDeck(), "Deck should be shuffled");
        assertEquals(52, deck.getDeck().size(), "Shuffling should not change the number of cards");
    }

    @Test
    void testRefillDeck() {
        Deck deck = new Deck();
        DiscardPile discardPile = new DiscardPile();

        // Add some cards to the discard pile
        Card card1 = new Card(Card.Suit.HEARTS, Card.Rank.ACE);
        Card card2 = new Card(Card.Suit.DIAMONDS, Card.Rank.TWO);
        discardPile.getDiscardPile().add(card1);
        discardPile.getDiscardPile().add(card2);

        // Test refilling the deck from the discard pile
        Deque<Card> originalDeck = new LinkedList<>(deck.getDeck());
        Deque<Card> refilledDeck = deck.refillDeck(deck, discardPile);

        assertNotEquals(originalDeck, refilledDeck, "Deck should be refilled from the discard pile");
        assertEquals(52, refilledDeck.size(), "Refilled deck should have 52 cards");
        assertTrue(refilledDeck.contains(card1) && refilledDeck.contains(card2),
                "Refilled deck should contain cards from the discard pile");
    }

    @Test
    void testDisplayDeck() {
        // This test just checks if the displayDeck method runs without errors
        Deck deck = new Deck();
        deck.displayDeck();
    }
}
