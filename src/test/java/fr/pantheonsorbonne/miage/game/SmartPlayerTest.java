package fr.pantheonsorbonne.miage.game;

import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.utils.Card;
import fr.pantheonsorbonne.miage.utils.Deck;
import fr.pantheonsorbonne.miage.utils.DiscardPile;
import fr.pantheonsorbonne.miage.utils.Player;
import fr.pantheonsorbonne.miage.utils.SmartPlayer;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Deque;
import java.util.PriorityQueue;



public class SmartPlayerTest {

    @Test
    void testInitialization() {
        Player smartPlayer = new SmartPlayer("Alice");
        assertEquals("Alice", smartPlayer.getName(), "Player should be initialized with the correct name");
        assertTrue(smartPlayer.getHand().isEmpty(), "Player's hand should be empty initially");
        assertEquals(0, smartPlayer.getPoints(), "Player's points should be initialized to zero");
    }

    @Test
    void testGetCardsToDiscard() {
        Player smartPlayer = new SmartPlayer("Bob");
        PriorityQueue<Card> hand = new PriorityQueue<>();
        hand.add(new Card(Card.Suit.HEARTS, Card.Rank.ACE));
        hand.add(new Card(Card.Suit.DIAMONDS, Card.Rank.ACE));
        hand.add(new Card(Card.Suit.CLUBS, Card.Rank.ACE));
        hand.add(new Card(Card.Suit.SPADES, Card.Rank.ACE));
        hand.add(new Card(Card.Suit.HEARTS, Card.Rank.KING));

        Deque<Card> discardedCards = smartPlayer.getCardsToDiscard(hand);

        assertEquals(1, discardedCards.size(), "Smart player should discard KING");
    }

    @Test
    void testPlay() {
        Player smartPlayer = new SmartPlayer("Charlie");
        DiscardPile discardPile = new DiscardPile();
        Deck deck = new Deck();

        discardPile.getDiscardPile().add(new Card(Card.Suit.CLUBS, Card.Rank.FOUR));

    
        smartPlayer.getHand().add(new Card(Card.Suit.HEARTS, Card.Rank.EIGHT));
        smartPlayer.getHand().add(new Card(Card.Suit.HEARTS, Card.Rank.FOUR));

        smartPlayer.play(discardPile, deck, smartPlayer.getHand());
        assertFalse(smartPlayer.getHand().contains(new Card(Card.Suit.HEARTS, Card.Rank.FOUR)));
    }

}

