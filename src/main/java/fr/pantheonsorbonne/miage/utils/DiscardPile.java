package fr.pantheonsorbonne.miage.utils;

import java.util.Deque;
import java.util.LinkedList;

public class DiscardPile {

    Deque<Card> discardPile = new LinkedList<Card>();


    public DiscardPile() {
        this.discardPile = new LinkedList<Card>();
    }

    public Deque<Card> getDiscardPile() {
        return discardPile;
    }

    public void setDiscardPile(Deque<Card> discardPile) {
        this.discardPile = discardPile;
    }
    

    public void addAll(Deque<Card> cardsToDiscard) {
        discardPile.addAll(cardsToDiscard);
    } 
    
    public void displayDiscardPile() {
        System.out.println("DiscardPile : ");
        for (Card card : discardPile) {
            System.out.print(card.getSuit() + "-" + card.getRank() + " ");
        }
    
        System.out.println(); 
    }
    
    
}
