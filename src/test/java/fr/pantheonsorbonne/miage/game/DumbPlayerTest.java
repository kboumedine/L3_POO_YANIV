package fr.pantheonsorbonne.miage.game;

import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.utils.Card;
import fr.pantheonsorbonne.miage.utils.Deck;
import fr.pantheonsorbonne.miage.utils.DiscardPile;
import fr.pantheonsorbonne.miage.utils.DumbPlayer;
import fr.pantheonsorbonne.miage.utils.Player;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Deque;
import java.util.PriorityQueue;

public class DumbPlayerTest {

    @Test
    void testInitialization() {
        Player dumbPlayer = new DumbPlayer("Alice");
        assertEquals("Alice", dumbPlayer.getName(), "Player should be initialized with the correct name");
        assertTrue(dumbPlayer.getHand().isEmpty(), "Player's hand should be empty initially");
        assertEquals(0, dumbPlayer.getPoints(), "Player's points should be initialized to zero");
    }

    @Test
    void testGetCardsToDiscard() {
        Player dumbPlayer = new DumbPlayer("Bob");
        PriorityQueue<Card> hand = new PriorityQueue<>();
        hand.add(new Card(Card.Suit.HEARTS, Card.Rank.ACE));
        hand.add(new Card(Card.Suit.DIAMONDS, Card.Rank.KING));

        Deque<Card> discardedCards = dumbPlayer.getCardsToDiscard(hand);

        assertEquals(1, discardedCards.size(), "Dumb player should discard one card (the top of the hand)");
    }

    @Test
    void testPlay() {
        Player dumbPlayer = new DumbPlayer("Charlie");
        DiscardPile discardPile = new DiscardPile();
        Deck deck = new Deck();

        // Set up the player's hand with a card
        dumbPlayer.getHand().add(new Card(Card.Suit.CLUBS, Card.Rank.SEVEN));

        // Redirect console output to check the printed result
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        dumbPlayer.play(discardPile, deck, dumbPlayer.getHand());

        String printedOutput = outputStream.toString().trim();
        assertFalse(printedOutput.contains("drawn from discard pile"),
                "Dumb player should not draw from discard pile");

        // Reset System.out to the console
        System.setOut(System.out);
    }

    // Add more tests as needed to cover other methods and edge cases.
}

