package fr.pantheonsorbonne.miage.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.utils.DumbPlayer;
import fr.pantheonsorbonne.miage.utils.InitGame;
import fr.pantheonsorbonne.miage.utils.Player;
import fr.pantheonsorbonne.miage.utils.SmartPlayer;

public class InitGameTest {

    InitGame initGame = new InitGame();

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

        // Set points for the players
        lowPointsPlayer.totalPoint = 5;
        highPointsPlayer.totalPoint = 15;

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
    
}
