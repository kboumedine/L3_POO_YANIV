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
    public void drawFromDeck(Deck deck, DiscardPile discardPile) {
        // TODO Auto-generated method stub
        super.drawFromDeck(deck, discardPile);
    }

    @Override
    public void play(DiscardPile discardPile, Deck deck, PriorityQueue<Card> hand) {
        discard(discardPile);
        drawFromDeck(deck, discardPile);
    }

    @Override
    public Deque<Card> getCardsToDiscard(PriorityQueue<Card> hand) {
        Deque<Card> discardedCards = new ArrayDeque<>();
        discardedCards.add(hand.peek());
        return discardedCards;
    }

    @Override
    public void discard(DiscardPile discardPile) {
        // TODO Auto-generated method stub
        super.discard(discardPile);
    }





    

    
    
    
    
}
