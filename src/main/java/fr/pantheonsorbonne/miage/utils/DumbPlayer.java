package fr.pantheonsorbonne.miage.utils;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;


public class DumbPlayer extends Player{




    public DumbPlayer(String name) {
        super(name);
        //TODO Auto-generated constructor stub
    }

    @Override
    public PriorityQueue<Card> getHand() {
        // TODO Auto-generated method stub
        return super.getHand();
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return super.getName();
    }

    @Override
    public Deque<Card> getCardsToDiscard(PriorityQueue<Card> hand) {

        Deque<Card> discardedCards = new ArrayDeque<Card>();
        discardedCards.add(hand.poll());
        return discardedCards;
        
    }


    @Override
    public void discard(DiscardPile discardPile) {
        // TODO Auto-generated method stub
        super.discard(discardPile);
    }

    @Override
    public void drawFromDeck(Deck deck) {
        // TODO Auto-generated method stub
        super.drawFromDeck(deck);
    }



    

    
    
    
    
}
