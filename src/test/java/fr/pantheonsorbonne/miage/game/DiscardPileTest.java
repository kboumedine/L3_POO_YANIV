package fr.pantheonsorbonne.miage.game;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

import fr.pantheonsorbonne.miage.utils.Card;
import fr.pantheonsorbonne.miage.utils.DiscardPile;

public class DiscardPileTest {

    @Test
    void testGetDiscardPile() {
        // Arrange
        DiscardPile discardPile = new DiscardPile();
        Deque<Card> expectedDiscardPile = new ArrayDeque<>();
        expectedDiscardPile.add(new Card(Card.Suit.HEARTS, Card.Rank.TEN));
        expectedDiscardPile.add(new Card(Card.Suit.DIAMONDS, Card.Rank.JACK));

        // Act
        discardPile.setDiscardPile(expectedDiscardPile);
        Deque<Card> actualDiscardPile = discardPile.getDiscardPile();

        // Assert
        assertEquals(expectedDiscardPile, actualDiscardPile, "getDiscardPile should return the set discard pile");
    }

    @Test
    void testSetDiscardPile() {
        // Arrange
        DiscardPile discardPile = new DiscardPile();
        Deque<Card> newDiscardPile = new ArrayDeque<>();
        newDiscardPile.add(new Card(Card.Suit.CLUBS, Card.Rank.QUEEN));
        newDiscardPile.add(new Card(Card.Suit.SPADES, Card.Rank.KING));

        // Act
        discardPile.setDiscardPile(newDiscardPile);

        // Assert
        assertEquals(newDiscardPile, discardPile.getDiscardPile(), "setDiscardPile should update the discard pile");
    }

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

    @Test
    void testDisplayDiscardPile() {
        // Arrange
        DiscardPile discardPile = new DiscardPile();
        Deque<Card> cards = new ArrayDeque<>();
        cards.add(new Card(Card.Suit.HEARTS, Card.Rank.TEN));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.Rank.JACK));
        discardPile.setDiscardPile(cards);

        // Redirect System.out to capture printed content
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Act
        discardPile.displayDiscardPile();

        // Assert
        String expectedOutput = "DiscardPile : HEARTS-TEN DIAMONDS-JACK\n";
        assertEquals(expectedOutput, outputStream.toString(), "displayDiscardPile should print the correct content");

        // Reset System.out to its original value
        System.setOut(System.out);
    }

}

