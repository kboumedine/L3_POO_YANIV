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

            //deck.initializeDeck();

            deck.shuffleDeck();
            // Initialisez la main du joueur à partir du deck
            player.initHand(deck);
            
            // Ajoutez le joueur à l'ensemble de joueurs
            players.add(player);
        }

        return players;
        

    }

    public static void main(String[] args) {
        
        InitGame initGame = new InitGame();
        List<Player> players = initGame.initPlayers();

        DiscardPile discardPile = new DiscardPile();
        Deck deck = new Deck();

        Card randomCard = deck.getDeck().pollFirst();  // Retirez la première carte du deck
        discardPile.getDiscardPile().add(randomCard);

        for(;;){
            for (Player player : players) {
                PriorityQueue<Card> hand = player.getHand();
                player.displayHand(hand);
                System.out.println(player.getPoints());
                Card lastCardDiscarded = discardPile.getDiscardPile().peekLast();
                player.discard(discardPile);
                if(!(discardPile.getDiscardPile().isEmpty()) && (lastCardDiscarded.getYanivValue()<7)){
                    player.drawFromDiscardPile(lastCardDiscarded);
                    System.out.println("ici");
                } else{
                    player.drawFromDeck(deck);
                }
                player.displayHand(hand);
                System.out.println(player.getPoints());
                if(player.getPoints()<15){
                    System.out.println("Player "+ player.getName() + " win");
                    return;
                }
            }                              //beug ca tej les as
                                

            discardPile.displayDiscardPile();
        }

    }


}
