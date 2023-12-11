package fr.pantheonsorbonne.miage.utils;

import java.util.Collections;
import java.util.Deque;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

import fr.pantheonsorbonne.miage.utils.Card.Suit;
import fr.pantheonsorbonne.miage.utils.combinations.HandleCombination;
import fr.pantheonsorbonne.miage.utils.combinations.Pair;
import fr.pantheonsorbonne.miage.utils.combinations.Suite;
import fr.pantheonsorbonne.miage.utils.specialrules.SpecialRules;



public class InitGame implements SpecialRules{


    private Deck deck = new Deck();
    private DiscardPile discardPile = new DiscardPile();
    public LinkedList<Player> players = new LinkedList<>();

    private int nbRound = 1;
    private Player potentialRoundWinner;
    private String specialAction = "";

    public void launchGame(){
        deck.initializeDeck();
        deck.shuffleDeck();
        initDiscardPile();
        initPlayers();
    }

    public List<Player> initPlayers(){          

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

        boolean skipNextTurn = false;
        boolean finishTheSuitOrDraw = false;
        boolean reverseOrder = false;
        Player startFromHere = null;
        Suit motif = null;


        for(;;){
            for (int i=0; i<players.size(); i++) {


                Player player = players.get(i);
                PriorityQueue<Card> hand = player.getHand();

                if(reverseOrder){
                    if(player!=startFromHere){
                        continue;
                    }else{
                        reverseOrder = false;
                        continue;
                    }
                }

                if(canDeclareYaniv(player)){
                    potentialRoundWinner = player;
                    resumeRound();
                    eliminatePlayers();
                    return;
                }


                if(skipNextTurn){
                    skipNextTurn = false;
                    continue;
                }

                if(finishTheSuitOrDraw){
                    Card card = new Card(motif, Card.Rank.KING);
                    if(player.getHand().contains(card)){
                        player.getHand().remove(card);
                        discardPile.getDiscardPile().add(card);
                        specialAction += player.getName() + " finished the TEN-QUEEN-JACK sequence.";
                        continue;
                    }else{
                        player.drawFromDeck(deck, discardPile);
                        specialAction += player.getName() + " couldn't finish the TEN-QUEEN-JACK sequence, he drew from deck.";
                    }
                }

                //player.displayHand(hand);
                //System.out.println(player.getPoints());

                if(shouldSkipNextPlayerTurn(player)){

                    specialAction += player.getName()+" skipped next player turn.\n";
                    skipNextTurn = true ;
                }

                if(shouldNextPlayerFinishTheSuitOrDraw(player)){
                    motif = getSuitOfSpecificSequence(player.getCardsToDiscard(player.getHand()));
                    finishTheSuitOrDraw = true;
                }

                if(shouldExchangeWithOtherPlayer(player)){
                    
                    specialAction += player.getName()+" exchanged his highest card with another player.\n";
                    if(i!=players.size()-1){
                    exchangeWithOtherPlayer(player, players.get(i+1));
                    }else{
                        exchangeWithOtherPlayer(player, players.get(1));
                    }
                }

                if (shouldReverseSense(player)) {
                    specialAction += player.getName()+" changed the direction of the game.\n";
                    reversePlayerOrder();
                    startFromHere = player;
                    reverseOrder = true; // Inverse le sens du jeu
                }

                player.play(discardPile, deck, hand);
                //player.displayHand(hand);
                //System.out.println(player.getPoints());
                //System.out.println();

            }
        }
    }


    public boolean canDeclareYaniv(Player player){
        return player.getPoints() <= 12;
    }

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

        int points = potentialRoundWinner.getPoints();
        boolean validateYaniv = true;
        String yassaf = null;
        String announceWinner = null;
        for(Player player : players){
            if(!player.equals(potentialRoundWinner) && player.getPoints()<points){
                yassaf = potentialRoundWinner.getName()+" declared yaniv but "+player.getName()+" had less points. He got a 30-points penalty !";
                potentialRoundWinner.totalPoint+=30;
                validateYaniv = false;
                break;
            }
            else{
                announceWinner = potentialRoundWinner.getName()+" declared yaniv and won the round scoring 0 point !";
                potentialRoundWinner.getHand().clear();
            }
        }
        System.out.println("ROUND "+ nbRound);
        nbRound++;
        System.out.println();
        System.out.println("   Main actions of the round :");
        System.out.println();
        System.out.println(specialAction);
        if(validateYaniv){
            System.out.println(announceWinner);
        }else{
            System.out.println(yassaf);
        }
        System.out.println();
        for (Player player : players) {
            player.totalPoint += player.getPoints();
            System.out.println("     "+player.getName()+" scored "+player.getPoints()+".     Total : "+player.totalPoint);
        }
        System.out.println();
        
    } 

    @Override
    public boolean shouldReverseSense(Player player) {

        if(Pair.hasPairOf(player.getCardsToDiscard(player.getHand()),7)){
            return true;
        }
        return false;

    }

    private void reversePlayerOrder() {
        Collections.reverse(players);
    }

    @Override
    public boolean shouldSkipNextPlayerTurn(Player player) {
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
    public boolean shouldNextPlayerFinishTheSuitOrDraw(Player player) {
        if(HandleCombination.hasSuite(player.getHand())){
            Deque<Card> sequence = Suite.getSuit(player.getHand());
            Suit motif = getSuitOfSpecificSequence(sequence);
            if(sequence.contains(new Card(motif, Card.Rank.TEN)) && sequence.contains(new Card(motif, Card.Rank.JACK)) && sequence.contains(new Card(motif, Card.Rank.QUEEN))){
                return true;
            }
        }
        return false;
    }

    private Suit getSuitOfSpecificSequence(Deque<Card> specificSuite) {
        Card card = specificSuite.peek();
        Suit motif = card.getSuit();
        return motif;
    }

}

