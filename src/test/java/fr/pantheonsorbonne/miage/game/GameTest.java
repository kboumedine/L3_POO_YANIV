package fr.pantheonsorbonne.miage.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


import java.util.LinkedList;

import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.utils.Game;
import fr.pantheonsorbonne.miage.utils.InitGame;
import fr.pantheonsorbonne.miage.utils.Player;
import fr.pantheonsorbonne.miage.utils.SmartPlayer;

public class GameTest {

    @Test
    void testPlayGame() {
        // Arrange
        Game game = new Game();

        // Créer une instance réelle de la classe InitGame
        InitGame initGame = new InitGame();
        LinkedList<Player> players = new LinkedList<>();
        players.add(new SmartPlayer("Winner")); // Assumant SmartPlayer est une classe valide
        initGame.players = players;  // Accéder directement au champ (attention : cela dépend de la visibilité du champ)  // Accéder directement au champ (attention : cela dépend de la visibilité du champ)

        // Act
        game.playGame();

        // Assert
        assertNotNull(game.getGameWinner(), "The game winner should not be null");
        assertEquals("Winner", game.getGameWinner().getName(), "The game winner should be the player named 'Winner'");
    }
    
}
