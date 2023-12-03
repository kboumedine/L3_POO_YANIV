package fr.pantheonsorbonne.miage.utils;

import java.util.Deque;
import java.util.PriorityQueue;


public abstract class Player {

    protected String name;

    public PriorityQueue<Card> hand = new PriorityQueue<Card>();

    int totalPoint = 0;


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



    public void initHand(Deck deck){
        int numCardsToDraw = 7; // ou la taille de la main souhaitée

        for (int i = 0; i < numCardsToDraw && !(deck.getDeck().isEmpty()); i++) {
            Card drawnCard = (deck.getDeck().pop());
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
    public abstract void play(DiscardPile discardPile, Deck deck, PriorityQueue<Card> hand);



    public void discard(DiscardPile discardPile){
        Deque<Card> cardsToDiscard = getCardsToDiscard(hand);
        discardPile.addAll(cardsToDiscard);
        hand.removeAll(cardsToDiscard);
    }


    public void drawFromDeck(Deck deck){
        if (deck.getDeck().isEmpty()) {
            // Lancez une exception indiquant que le deck est vide.
            System.out.println("Le deck est vide. Impossible de piocher.");
        }
        Card drawnCard = deck.getDeck().pop();
        hand.add(drawnCard);
    }
    
    public void drawFromDiscardPile(Card lastCardDiscarded){
        hand.add(lastCardDiscarded);
    }

    public int getPoints() {
        int points = 0;
    
        for (Card card : hand) {
            points += card.getYanivValue();
        }
    
        return points;
    }

    public int getTotalPoint(int point){
        totalPoint += point;
        return totalPoint;
    }


    public void clearHand(){
        while(!hand.isEmpty()){
            hand.poll();
        }
        
    }




}

