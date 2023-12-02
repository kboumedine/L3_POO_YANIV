package fr.pantheonsorbonne.miage.utils;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

import fr.pantheonsorbonne.miage.utils.combinations.HandleCombination;
import fr.pantheonsorbonne.miage.utils.combinations.Pair;
import fr.pantheonsorbonne.miage.utils.combinations.ThreeOfAKind;
import fr.pantheonsorbonne.miage.utils.combinations.FourOfAKind;

public class SmartPlayer extends Player{

    public SmartPlayer(String name) {
        super(name);
        //TODO Auto-generated constructor stub
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return super.getName();
    }

    @Override
    public PriorityQueue<Card> getHand() {
        // TODO Auto-generated method stub
        return super.getHand();
    }




    @Override
    public Deque<Card> getCardsToDiscard(PriorityQueue<Card> hand) {
        
        Deque<Card> discardedCards = new ArrayDeque<Card>();


        if(HandleCombination.hasFourOfAKind(hand)){
            Deque<Card> fourOfAKind = FourOfAKind.getFourOfAKind(hand);
            discardedCards.addAll(fourOfAKind);
        }else if (HandleCombination.hasThreeOfAKind(hand)){
            Deque<Card> threeOfAKind = ThreeOfAKind.getThreeOfAKind(hand);
            discardedCards.addAll(threeOfAKind);
        }else if(HandleCombination.hasPair(hand)){
            Deque<Card> pair = Pair.getHighestPair(hand);
            discardedCards.addAll(pair);
        }else{

            discardedCards.add(hand.poll());
        }

        
        return discardedCards;
    }


    @Override
    public void discard(DiscardPile discardPile) {
        // TODO Auto-generated method stub
        super.discard(discardPile);
    }
  
    
}
