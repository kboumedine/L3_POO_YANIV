package fr.pantheonsorbonne.miage.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;


public class Deck {

    private Deque<Card> deck;   //voir si c pas mieux de l'implementer a partir de ArrayDeque

    public Deck() {
        this.deck = new LinkedList<Card>();
        initializeDeck();
    }

    public Deck(Deque<Card> deck) {
        this.deck = new LinkedList<>(deck);
    }
    
    public void initializeDeck(){
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                deck.add(new Card(suit, rank));
            }
        }
    }



    public Deque<Card> getDeck() {
        return deck;
    }
   

    public void shuffleDeck() {
        List<Card> deckList = new ArrayList<>(deck);
        Collections.shuffle(deckList);
        deck = new LinkedList<>(deckList);
    }

    public Deque<Card> refillDeck(Deck deck, DiscardPile discardPile){
        Card keepFisrtCard = discardPile.getDiscardPile().peekFirst();
        deck.getDeck().addAll(discardPile.getDiscardPile());
        discardPile.getDiscardPile().add(keepFisrtCard);
        return deck.getDeck();
    }
    
    
    
}
