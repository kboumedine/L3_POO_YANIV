package fr.pantheonsorbonne.miage.utils;


import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;


public class InitGame {
    
    Deck deck = new Deck();
    
    List<Player> players = new ArrayList<>();

    public List<Player> initPlayers(){

        int numPlayers = 5; // Nombre de joueurs que vous souhaitez initialiser

        for (int i = 1; i <= numPlayers; i++) {
            // String playerName = "Player " + i;
            
            Player player;
            if (i % 2 == 0) {
                player = new DumbPlayer("DumbPlayer "+ i);
            } else {
                player = new SmartPlayer("SmartPlayer "+ i);
            }

            deck.shuffleDeck();
            // Initialisez la main du joueur à partir du deck
            player.initHand(deck.getDeck());
            
            // Ajoutez le joueur à l'ensemble de joueurs
            players.add(player);
        }

        return players;
        

    }

    public static void main(String[] args) {
        
        InitGame initGame = new InitGame();
        List<Player> players = initGame.initPlayers();

        DiscardPile discardPile = new DiscardPile();

        // Affichez la main de chaque joueur
        for (Player player : players) {
            PriorityQueue<Card> hand = player.getHand();
            player.displayHand(hand);
            player.discard(discardPile);
            player.displayHand(hand);
        }                                                           //beug ca tej les as

        discardPile.displayDiscardPile();

    }


}
