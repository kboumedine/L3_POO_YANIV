package fr.pantheonsorbonne.miage.utils;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

import fr.pantheonsorbonne.miage.utils.specialrules.SpecialRules;
import fr.pantheonsorbonne.miage.utils.combinations.Pair;

public class InitGame implements SpecialRules{

    private Deck deck = new Deck();
    private DiscardPile discardPile = new DiscardPile();
    public List<Player> players = new LinkedList<>();

    public void launchGame(){
        deck.initializeDeck();
        deck.shuffleDeck();
        initDiscardPile();
        initPlayers();
    }

    public List<Player> initPlayers(){          // verifier l'unicité des cartes 

        int numPlayers = 5;

        for (int i = 1; i <= numPlayers; i++) {
            
            Player player;
            if (i % 2 == 0) {
                player = new DumbPlayer("DumbPlayer "+ i);
            } else {
                player = new SmartPlayer("SmartPlayer "+ i);
            }

            player.initHand(deck);
            Card card = player.getHand().peek();
            System.out.println(card.getSuit()+" " + card.getRank());
            
            players.add(player);
        
        }

        return players;

    }

    public void initDiscardPile(){
        Card randomCard = deck.getDeck().pollFirst();  
        discardPile.getDiscardPile().add(randomCard);
    }

    public void playRound(){

        boolean skipNextTurn = false;

        for(;;){
            for (int i=0; i<players.size(); i++) {


                if(skipNextTurn){
                    skipNextTurn = false;
                    continue;
                }

                Player player = players.get(i);
                PriorityQueue<Card> hand = player.getHand();
                player.displayHand(hand);
                System.out.println(player.getPoints());

                if(shouldSkipNextTurn(player)){
                    System.out.println("ok");
                    skipNextTurn = true ;
                }

                if(shouldExchangeWithOtherPlayer(player)){
                    if(i!=players.size()){
                    exchangeWithOtherPlayer(player, players.get(i+1));
                    System.out.println("a echangé");
                    }else{
                        exchangeWithOtherPlayer(player, players.get(1));
                        System.out.println("a echangé");
                    }
                }

                player.play(discardPile, deck, hand);
                player.displayHand(hand);
                System.out.println(player.getPoints());
                System.out.println();

                if(canDeclareYaniv(player)){
                    System.out.println(player.getName()+"win");
                    resumeRound();
                    eliminatePlayers();
                    return;
                }
            }
        }
    }


    public boolean canDeclareYaniv(Player player){
        return player.getPoints() <= 7;
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
            player.totalPoint += player.getPoints();
            System.out.println("Player "+player.getName()+" scored "+player.getPoints()+".     Total : "+player.totalPoint);
        }
    } 

    public void eliminatePlayers(){
        Set<Player> eliminatedPlayers = new LinkedHashSet<>();
        for (Player player : players) {
            if(player.totalPoint>=100){
                eliminatedPlayers.add(player);
                System.out.println("Player "+player.getName()+" is eliminated.");
            }
        }
        players.removeAll(eliminatedPlayers);
    }

    @Override
    public boolean shouldReverseSense(Player player) {

        if(Pair.hasPairOf(player.getCardsToDiscard(player.getHand()),7)){
            return true;
        }
        return false;

    }

    @Override
    public boolean shouldSkipNextTurn(Player player) {
        if(Pair.hasPairOf(player.getCardsToDiscard(player.getHand()),8)){
            return true;
        }
        return false;
    }

    @Override
    public boolean shouldExchangeWithOtherPlayer(Player player) {
        if(Pair.hasPairOf(player.getCardsToDiscard(player.getHand()),9)){
            return true;
        }
        return false;
    }

    @Override
    public void exchangeWithOtherPlayer(Player player1, Player player2) {
        Card pickedCard = player2.getHand().poll();
        Card givenCard = player1.getHand().poll();
        player1.getHand().add(pickedCard);
        player2.getHand().add(givenCard);
    }

    @Override
    public void finishTheSuitOrDraw() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'finishTheSuitOrDraw'");
    }
}
