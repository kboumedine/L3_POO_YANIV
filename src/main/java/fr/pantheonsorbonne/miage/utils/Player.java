package fr.pantheonsorbonne.miage.utils;

import java.util.Deque;
import java.util.PriorityQueue;


public abstract class Player {

    protected String name;

    public PriorityQueue<Card> hand = new PriorityQueue<Card>();


    public Player(String name) {
        this.name = name;
        this.hand = new PriorityQueue<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PriorityQueue<Card> getHand() {
        return hand;
    }

    public void setHand(PriorityQueue<Card> hand) {
        this.hand = hand;
    }



    public void initHand(Deque<Card> deck){
        int numCardsToDraw = 7; // ou la taille de la main souhaitée

        for (int i = 0; i < numCardsToDraw && !deck.isEmpty(); i++) {
            Card drawnCard = deck.pop();
            hand.add(drawnCard);
        }
    }

    public void displayHand(PriorityQueue<Card> hand) {
        System.out.println(name + "'s hand: ");
        for (Card card : hand) {
            System.out.print(card.getSuit() + "-" + card.getRank() + " ");
        }
    
        System.out.println(); 
    }


    public abstract Deque<Card> getCardsToDiscard(PriorityQueue<Card> hand);



    public void discard(DiscardPile discardPile){
        Deque<Card> cardsToDiscard = getCardsToDiscard(hand);
        discardPile.addAll(cardsToDiscard);
        hand.removeAll(cardsToDiscard);
    }


    public void drawFromDeck(Deque<Card> deck){
        Card drawnCard = deck.pop();
        hand.add(drawnCard);
    }
    
    public void drawFromDiscardPile(Deque<Card> discardPile){
        Card drawnCard = discardPile.pop();
        hand.add(drawnCard);
    }

}

