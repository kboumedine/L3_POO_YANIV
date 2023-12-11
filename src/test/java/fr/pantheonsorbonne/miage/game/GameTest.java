package fr.pantheonsorbonne.miage.game;


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
      
        Game game = new Game();

        InitGame initGame = new InitGame();
        LinkedList<Player> players = new LinkedList<>();
        players.add(new SmartPlayer("Winner")); 
        initGame.players = players;  
     
        game.playGame();

        
        assertNotNull(game.getGameWinner(), "The game winner should not be null");

    }
    
}
