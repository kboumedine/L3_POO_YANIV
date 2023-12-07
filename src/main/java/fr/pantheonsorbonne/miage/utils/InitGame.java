package fr.pantheonsorbonne.miage.utils;

import java.util.Deque;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

import fr.pantheonsorbonne.miage.utils.specialrules.SpecialRules;
import fr.pantheonsorbonne.miage.utils.Card.Rank;
import fr.pantheonsorbonne.miage.utils.Card.Suit;
import fr.pantheonsorbonne.miage.utils.combinations.Pair;
import fr.pantheonsorbonne.miage.utils.combinations.Suite;


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

    public List<Player> initPlayers(){          

        int numPlayers = 5;

        Player player1 = new SmartPlayer("special");
        PriorityQueue<Card> hand = player1.getHand();
        hand.add(new Card(Card.Suit.HEARTS, Card.Rank.TEN));
        hand.add(new Card(Card.Suit.DIAMONDS, Card.Rank.TWO));
        hand.add(new Card(Card.Suit.HEARTS, Card.Rank.QUEEN));
        hand.add(new Card(Card.Suit.HEARTS, Card.Rank.JACK));
        hand.add(new Card(Card.Suit.CLUBS, Card.Rank.KING));
        players.add(player1);

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
        boolean finishTheSuitOrDraw = false;

        for(;;){
            for (int i=0; i<players.size(); i++) {

                Player player = players.get(i);
                PriorityQueue<Card> hand = player.getHand();


                if(skipNextTurn){
                    skipNextTurn = false;
                    continue;
                }

                if(finishTheSuitOrDraw){
                    Suit targetSuit = getSuitOfSpecificSequence(player.getCardsToDiscard(player.getHand()));
                    for (Card card : hand) {
                        if (card.getSuit() == targetSuit && card.getRank() == Rank.KING) {
                            hand.remove(card);
                            System.out.println("il avait le king");
                            break; 

                        }else{
                            System.out.println("il avait pas le king");
                            player.drawFromDeck(deck, discardPile);
                        }
                    }
                    finishTheSuitOrDraw = false;
                    continue;
                }

                player.displayHand(hand);
                System.out.println(player.getPoints());

                if(shouldSkipNextTurn(player)){
                    skipNextTurn = true ;
                }

                if(shouldNextPlayerFinishTheSuitOrDraw(player)){
                    finishTheSuitOrDraw = true;
                }

                if(shouldExchangeWithOtherPlayer(player)){
                    if(i!=players.size()-1){
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
    public boolean shouldNextPlayerFinishTheSuitOrDraw(Player player) {
        if(Suite.hasTenJackQueenSequence(player.getCardsToDiscard(player.getHand()))){
            return true;
        }
        return false;
    }

    private Suit getSuitOfSpecificSequence(Deque<Card> specificSuite) {
        // Retourner le motif de la première carte de la suite spécifique
        return specificSuite.iterator().next().getSuit();
    }

}
