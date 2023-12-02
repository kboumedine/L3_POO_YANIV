package fr.pantheonsorbonne.miage.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Deck {

    Deque<Card> deck = new LinkedList<Card>();   //voir si c pas mieux de l'implementer a partir de ArrayDeque

    public Deck(Deque<Card> deck) {
        this.deck = deck;
    }

    public Deck() {
        initializeDeck();
    }
    
    private void initializeDeck(){
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
    
    
    
}
