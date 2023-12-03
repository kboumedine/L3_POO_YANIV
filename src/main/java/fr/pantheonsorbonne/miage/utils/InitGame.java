package fr.pantheonsorbonne.miage.utils;

import java.util.LinkedHashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class initGame {

    private Deck deck = new Deck();
    private DiscardPile discardPile = new DiscardPile();
    private Set<Player> players = new LinkedHashSet<>();

    public void launchGame(){
        deck.initializeDeck();
        deck.shuffleDeck();
        initDiscardPile();
        initPlayers();
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
        Card randomCard = deck.getDeck().pollFirst();  
        discardPile.getDiscardPile().add(randomCard);
    }

    public void playRound(){

        for(;;){
            for (Player player : players) {
                //System.out.println("Discard Pile Size: " + discardPile.getDiscardPile().size());
                PriorityQueue<Card> hand = player.getHand();
                player.displayHand(hand);
                System.out.println(player.getPoints());
                player.play(discardPile, deck, hand);
                player.displayHand(hand);
                System.out.println(player.getPoints());
                System.out.println();
                if(canDeclareYaniv(player)){
                    System.out.println(player.getName()+"win");
                    resumeRound();
                    return;
                }
            }
        }
    }


    public boolean canDeclareYaniv(Player player){
        return player.getPoints() <= 20;
    }

    public void newRound(){
        for (Player player : players) {
            PriorityQueue<Card> hand = player.getHand();
            hand.clear();
        }
        discardPile.getDiscardPile().clear();
        deck.initializeDeck();
        deck.shuffleDeck();
        initDiscardPile();
        for (Player player : players) {
            player.initHand(deck);
        }
    }

    public void resumeRound(){
        for (Player player : players) {
            //PriorityQueue<Card> hand = player.getHand();
            System.out.println("Player "+player.getName()+" scored "+player.getPoints()+".     Total : "+player.getTotalPoint(player.getPoints()));
        }
    } 
}
