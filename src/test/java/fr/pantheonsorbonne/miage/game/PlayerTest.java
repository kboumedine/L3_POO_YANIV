package fr.pantheonsorbonne.miage.game;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import fr.pantheonsorbonne.miage.utils.Card;
import fr.pantheonsorbonne.miage.utils.Player;
import fr.pantheonsorbonne.miage.utils.SmartPlayer;
import fr.pantheonsorbonne.miage.utils.Deck;
import fr.pantheonsorbonne.miage.utils.DiscardPile;


public class PlayerTest {

    @Test
    void testInitialization() {
        Player player = new SmartPlayer("Alice");
        assertEquals("Alice", player.getName(), "Player should be initialized with the correct name");
        assertTrue(player.getHand().isEmpty(), "Player's hand should be empty initially");
        assertEquals(0, player.getPoints(), "Player's points should be initialized to zero");
    }

    @Test
    void testSetName() {
      
        Player player = new SmartPlayer("SmartPlayer1");
        
      
        assertEquals("SmartPlayer1", player.getName(), "Initial name should be SmartPlayer1");

        player.setName("NewName");

        assertEquals("NewName", player.getName(), "Name should be updated to NewName");
    }

    @Test
    void testInitHand() {
        Player player = new SmartPlayer("Bob");
        Deck deck = new Deck();
        player.initHand(deck);
        assertEquals(7, player.getHand().size(), "Player's hand should be initialized with 7 cards");
    }

    @Test
    void testDrawFromDeck() {
        Player player = new SmartPlayer("Charlie");
        Deck deck = new Deck();
        DiscardPile discardPile = new DiscardPile();

        player.drawFromDeck(deck, discardPile);

        assertEquals(1, player.getHand().size(), "Player's hand should have one card after drawing");
        assertEquals(51, deck.getDeck().size(), "Deck should have one less card after drawing");
    }

    @Test
    void testDrawFromDiscardPile() {
        Player player = new SmartPlayer("David");
        Card lastCardDiscarded = new Card(Card.Suit.HEARTS, Card.Rank.QUEEN);

        player.drawFromDiscardPile(lastCardDiscarded);

        assertEquals(1, player.getHand().size(), "Player's hand should have one card after drawing from discard pile");
        assertTrue(player.getHand().contains(lastCardDiscarded), "Player's hand should contain the drawn card");
    }

    @Test
    void testDiscard() {
        Player player = new SmartPlayer("Eva");
        DiscardPile discardPile = new DiscardPile();

        player.getHand().add(new Card(Card.Suit.SPADES, Card.Rank.TEN));
        player.getHand().add(new Card(Card.Suit.CLUBS, Card.Rank.SEVEN));
        player.getHand().add(new Card(Card.Suit.DIAMONDS, Card.Rank.FIVE));

        player.discard(discardPile);
        assertEquals(1, discardPile.getDiscardPile().size(), "Discard pile should have three cards after discarding");
    }

    @Test
    void testGetPoints() {
        Player player = new SmartPlayer("Frank");

        player.getHand().add(new Card(Card.Suit.HEARTS, Card.Rank.KING));
        player.getHand().add(new Card(Card.Suit.DIAMONDS, Card.Rank.SEVEN));

        assertEquals(20, player.getPoints(), "Player's points should be the sum of card values in the hand");
    }


}

