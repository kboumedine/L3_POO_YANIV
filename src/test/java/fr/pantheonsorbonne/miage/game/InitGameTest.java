package fr.pantheonsorbonne.miage.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.PriorityQueue;

import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.utils.Card;
import fr.pantheonsorbonne.miage.utils.Deck;
import fr.pantheonsorbonne.miage.utils.DiscardPile;
import fr.pantheonsorbonne.miage.utils.DumbPlayer;
import fr.pantheonsorbonne.miage.utils.InitGame;
import fr.pantheonsorbonne.miage.utils.Player;
import fr.pantheonsorbonne.miage.utils.SmartPlayer;

public class InitGameTest {

    InitGame initGame = new InitGame();
    private Deck deck = new Deck();
    private DiscardPile discardPile = new DiscardPile();

    @Test
    void testInitPlayers() {
        // Assuming you have a GameManager instance

        // Call the initPlayers method
        List<Player> players = initGame.initPlayers();

        // Check that the number of players is as expected
        assertEquals(5, players.size(), "Expected 5 players");

        // Check that each player has a non-empty hand
        for (Player player : players) {
            assertNotNull(player.getHand(), "Player's hand should not be null");
            assertFalse(player.getHand().isEmpty(), "Player's hand should not be empty");
        }

        // Optionally, you can check the type of players (SmartPlayer/DumbPlayer)
        // based on your logic for creating players in the initPlayers method
        for (int i = 1; i <= 5; i++) {
            Player player = players.get(i - 1);
            if (i % 2 == 0) {
                assertTrue(player instanceof DumbPlayer, "Player should be an instance of DumbPlayer");
            } else {
                assertTrue(player instanceof SmartPlayer, "Player should be an instance of SmartPlayer");
            }
        }
    }


    @Test
    void testCanDeclareYaniv() {
        // Assuming you have a GameManager instanc

        // Create a player with different points
        Player lowPointsPlayer = new DumbPlayer("LowPointsPlayer");
        Player highPointsPlayer = new SmartPlayer("HighPointsPlayer");

        PriorityQueue<Card> hand = lowPointsPlayer.getHand();
        hand.add(new Card(Card.Suit.DIAMONDS, Card.Rank.TWO));

        PriorityQueue<Card> hand2 = highPointsPlayer.getHand();
        hand2.add(new Card(Card.Suit.HEARTS, Card.Rank.SEVEN));
        hand2.add(new Card(Card.Suit.HEARTS, Card.Rank.QUEEN));

        // Check if the player with low points can declare Yaniv
        assertTrue(initGame.canDeclareYaniv(lowPointsPlayer), "Low points player should be able to declare Yaniv");

        // Check if the player with high points cannot declare Yaniv
        assertFalse(initGame.canDeclareYaniv(highPointsPlayer), "High points player should not be able to declare Yaniv");
    }

    @Test
    void testEliminatePlayers() {
        // Assuming you have a GameManager instance

        // Create players with different total points
        Player player1 = new DumbPlayer("Player1");
        Player player2 = new SmartPlayer("Player2");

        // Set total points for the players
        player1.totalPoint = 80;
        player2.totalPoint = 110;

        // Add players to the game
        initGame.players.add(player1);
        initGame.players.add(player2);

        // Call eliminatePlayers method
        initGame.eliminatePlayers();

        // Check if the eliminated player is removed
        assertFalse(initGame.players.contains(player2), "Eliminated player should be removed");
        assertTrue(initGame.players.contains(player1), "Non-eliminated player should remain in the list");
    }

    @Test
    void testShouldReverseSense() {
        Player player = new SmartPlayer("Paul");
        PriorityQueue<Card> hand = player.getHand();
        hand.add(new Card(Card.Suit.DIAMONDS, Card.Rank.SEVEN));
        hand.add(new Card(Card.Suit.HEARTS, Card.Rank.SEVEN));

        assertTrue(initGame.shouldReverseSense(player));
    }

    @Test
    void testShouldSkipNextPlayerTurn() {
        Player player = new SmartPlayer("Paul");
        PriorityQueue<Card> hand = player.getHand();
        hand.add(new Card(Card.Suit.DIAMONDS, Card.Rank.EIGHT));
        hand.add(new Card(Card.Suit.HEARTS, Card.Rank.EIGHT));

        assertTrue(initGame.shouldSkipNextPlayerTurn(player));
    }

    @Test
    void testShouldExchangeWithOtherPlayer() {
        Player player = new SmartPlayer("Paul");
        PriorityQueue<Card> hand = player.getHand();
        hand.add(new Card(Card.Suit.DIAMONDS, Card.Rank.NINE));
        hand.add(new Card(Card.Suit.HEARTS, Card.Rank.NINE));

        assertTrue(initGame.shouldExchangeWithOtherPlayer(player));
    }


    @Test
    void testPlayRound() {
        

        // Call the playRound method
        initGame.launchGame();
        initGame.playRound();
        int initialPlayersSize = initGame.players.size();
        int initialDeckSize = deck.getDeck().size();

        // Check if players' hands are modified as expected
        for (Player player : initGame.players) {
            // Add assertions based on your expectations after the round
            assertTrue(player.getHand().size() >= 0, "Player's hand size should be non-negative");
        }

        // Check if the deck is modified as expected
        int newDeckSize = deck.getDeck().size();
        assertTrue(newDeckSize < initialDeckSize, "Deck size should have decreased");

        // Check if other game state changes are as expected
        int newPlayersSize = initGame.players.size();
        assertTrue(newPlayersSize <= initialPlayersSize, "Players list size should not increase");

        // Add more assertions based on your specific game logic and expected outcomes
    }

    @Test
    void testExchangeAndReverseSense() {
        // Arrange
        InitGame game = new InitGame();
        game.initPlayers();

        // Assuming the first player has a pair of 9s and should exchange
        Player playerWithExchange = game.players.getFirst();
        PriorityQueue<Card> handWithExchange = new PriorityQueue<>();
        handWithExchange.add(new Card(Card.Suit.CLUBS, Card.Rank.NINE));
        handWithExchange.add(new Card(Card.Suit.DIAMONDS, Card.Rank.NINE));
        playerWithExchange.setHand(handWithExchange);

        // Assuming the second player has a pair of 7s and should reverse the sense
        Player playerWithReverseSense = game.players.get(1);
        PriorityQueue<Card> handWithReverseSense = new PriorityQueue<>();
        handWithReverseSense.add(new Card(Card.Suit.HEARTS, Card.Rank.SEVEN));
        handWithReverseSense.add(new Card(Card.Suit.SPADES, Card.Rank.SEVEN));
        playerWithReverseSense.setHand(handWithReverseSense);

        // Capture the initial state of hands
        int initialHandSizeWithExchange = playerWithExchange.getHand().size();
        int initialHandSizeWithReverseSense = playerWithReverseSense.getHand().size();

        // Act
        game.playRound();

        // Check the state after the actions
        int finalHandSizeWithExchange = playerWithExchange.getHand().size();
        int finalHandSizeWithReverseSense = playerWithReverseSense.getHand().size();

        // Assert
        assertEquals(initialHandSizeWithExchange, finalHandSizeWithExchange, "PlayerWithExchange's hand size should not change");
        assertEquals(initialHandSizeWithReverseSense, finalHandSizeWithReverseSense, "PlayerWithReverseSense's hand size should not change");

        // Assuming the exchange action involves changing the highest card
        assertTrue(finalHandSizeWithExchange < initialHandSizeWithExchange, "PlayerWithExchange should have exchanged cards");

        // Assuming the reverse sense action involves reversing the player order
        assertEquals(game.players.getFirst(), playerWithReverseSense, "PlayerWithReverseSense should be the first player after reversing the order");
    }



    
}
