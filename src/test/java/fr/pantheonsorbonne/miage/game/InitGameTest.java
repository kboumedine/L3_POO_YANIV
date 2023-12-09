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

    @Test
    void testInitPlayers() {
        // Assuming you have a GameManager instance
        InitGame initGame = new InitGame();

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
    
}
