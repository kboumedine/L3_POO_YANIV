package fr.pantheonsorbonne.miage.utils;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;



public class InitGame extends Engine{


    private Deck deck = new Deck();

    @Override
    public List<Player> initPlayers(){          

        int numPlayers = 5;

        /* 
        Player player1 = new SmartPlayer("special");
        PriorityQueue<Card> hand = player1.getHand();
        hand.add(new Card(Card.Suit.HEARTS, Card.Rank.SEVEN));
        hand.add(new Card(Card.Suit.DIAMONDS, Card.Rank.TWO));
        hand.add(new Card(Card.Suit.HEARTS, Card.Rank.QUEEN));
        hand.add(new Card(Card.Suit.HEARTS, Card.Rank.SEVEN));
        players.add(player1);  */

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

    @Override
    public void eliminatePlayers(){
        Set<Player> eliminatedPlayers = new LinkedHashSet<>();
        for (Player player : players) {
            if(player.totalPoint>=100){
                eliminatedPlayers.add(player);
                System.out.println("     "+player.getName()+" is eliminated.");
            }
        }
        System.out.println();
        players.removeAll(eliminatedPlayers);
    }

}
