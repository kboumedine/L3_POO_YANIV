package fr.pantheonsorbonne.miage.utils;

import java.util.LinkedHashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class initGame {

    private Deck deck = new Deck();
    private DiscardPile discardPile = new DiscardPile();
    private Set<Player> players = new LinkedHashSet<>();

    public void initializeGame() {
        deck.initializeDeck();
        deck.shuffleDeck();
        initPlayers();
        initDiscardPile();
        play();
    }

    public Set<Player> initPlayers(){

        int numPlayers = 5;

        for (int i = 1; i <= numPlayers; i++) {
            
            Player player;
            if (i % 2 == 0) {
                player = new DumbPlayer("DumbPlayer "+ i);
            } else {
                player = new SmartPlayer("SmartPlayer "+ i);
            }

            player.initHand(deck);
            
            players.add(player);
        }

        return players;

    }

    public void initDiscardPile(){
        Card randomCard = deck.getDeck().pollFirst();  // Retirez la première carte du deck
        discardPile.getDiscardPile().add(randomCard);
    }

    public void play(){

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
                if(player.getPoints()<=10){
                    System.out.println("Player "+ player.getName() + " win");
                    return;
                }
            }                              //beug ca tej les as
                                

            discardPile.displayDiscardPile();
        }


    }
    
}
