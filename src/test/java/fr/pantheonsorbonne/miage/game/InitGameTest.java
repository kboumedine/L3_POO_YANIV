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
       
        List<Player> players = initGame.initPlayers();

    
        assertEquals(5, players.size(), "Expected 5 players");

       
        for (Player player : players) {
            assertNotNull(player.getHand(), "Player's hand should not be null");
            assertFalse(player.getHand().isEmpty(), "Player's hand should not be empty");
        }

     
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
      
        Player lowPointsPlayer = new DumbPlayer("LowPointsPlayer");
        Player highPointsPlayer = new SmartPlayer("HighPointsPlayer");

        PriorityQueue<Card> hand = lowPointsPlayer.getHand();
        hand.add(new Card(Card.Suit.DIAMONDS, Card.Rank.TWO));

        PriorityQueue<Card> hand2 = highPointsPlayer.getHand();
        hand2.add(new Card(Card.Suit.HEARTS, Card.Rank.SEVEN));
        hand2.add(new Card(Card.Suit.HEARTS, Card.Rank.QUEEN));

        assertTrue(initGame.canDeclareYaniv(lowPointsPlayer), "Low points player should be able to declare Yaniv");

        assertFalse(initGame.canDeclareYaniv(highPointsPlayer), "High points player should not be able to declare Yaniv");
    }

    @Test
    void testEliminatePlayers() {
        
        Player player1 = new DumbPlayer("Player1");
        Player player2 = new SmartPlayer("Player2");

        player1.totalPoint = 80;
        player2.totalPoint = 110;

        initGame.players.add(player1);
        initGame.players.add(player2);

       
        initGame.eliminatePlayers();

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
        

        initGame.launchGame();
        initGame.playRound();
        int initialPlayersSize = initGame.players.size();
        int initialDeckSize = deck.getDeck().size();

      
        for (Player player : initGame.players) {
           
            assertTrue(player.getHand().size() >= 0, "Player's hand size should be non-negative");
        }

        int newDeckSize = deck.getDeck().size();
        assertTrue(newDeckSize <= initialDeckSize, "Deck size should have decreased or be the same");

      
        int newPlayersSize = initGame.players.size();
        assertTrue(newPlayersSize <= initialPlayersSize, "Players list size should not increase");

        
    }

    @Test
    void testExchangeAndReverseSense() {
   
        InitGame game = new InitGame();
        game.initPlayers();

        Player playerWithExchange = game.players.getFirst();
        PriorityQueue<Card> handWithExchange = new PriorityQueue<>();
        handWithExchange.add(new Card(Card.Suit.CLUBS, Card.Rank.NINE));
        handWithExchange.add(new Card(Card.Suit.DIAMONDS, Card.Rank.NINE));
        playerWithExchange.setHand(handWithExchange);

        Player playerWithReverseSense = game.players.get(1);
        PriorityQueue<Card> handWithReverseSense = new PriorityQueue<>();
        handWithReverseSense.add(new Card(Card.Suit.HEARTS, Card.Rank.SEVEN));
        handWithReverseSense.add(new Card(Card.Suit.SPADES, Card.Rank.SEVEN));
        playerWithReverseSense.setHand(handWithReverseSense);

        int initialHandSizeWithExchange = playerWithExchange.getHand().size();
        int initialHandSizeWithReverseSense = playerWithReverseSense.getHand().size();

        game.playRound();

        int finalHandSizeWithExchange = playerWithExchange.getHand().size();
        int finalHandSizeWithReverseSense = playerWithReverseSense.getHand().size();

        assertEquals(initialHandSizeWithExchange, finalHandSizeWithExchange, "PlayerWithExchange's hand size should not change");
        assertEquals(initialHandSizeWithReverseSense, finalHandSizeWithReverseSense, "PlayerWithReverseSense's hand size should not change");

    
        assertTrue(finalHandSizeWithExchange < initialHandSizeWithExchange, "PlayerWithExchange should have exchanged cards");

        assertEquals(game.players.getFirst(), playerWithReverseSense, "PlayerWithReverseSense should be the first player after reversing the order");
    }


    @Test
    void testNewRound() {
       
        InitGame game = new InitGame();
        game.launchGame();

    
        Player player1 = game.players.getFirst();
        Player player2 = game.players.get(1);

     
        game.newRound();


        assertTrue(discardPile.getDiscardPile().isEmpty(), "Discard pile should be empty");
        assertEquals(0, player1.getHand().size(), "Player 1's hand should be empty");
        assertEquals(0, player2.getHand().size(), "Player 2's hand should be empty");

    }
    
}
