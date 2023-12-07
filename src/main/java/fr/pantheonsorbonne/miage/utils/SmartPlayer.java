package fr.pantheonsorbonne.miage.utils;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

import fr.pantheonsorbonne.miage.utils.combinations.HandleCombination;
import fr.pantheonsorbonne.miage.utils.combinations.Pair;
import fr.pantheonsorbonne.miage.utils.combinations.ThreeOfAKind;
import fr.pantheonsorbonne.miage.utils.combinations.FourOfAKind;
import fr.pantheonsorbonne.miage.utils.combinations.Suite;


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
        int value = 0;
        if(HandleCombination.hasFourOfAKind(hand)){
            Deque<Card> fourOfAKind = FourOfAKind.getFourOfAKind(hand);
            for (Card card : fourOfAKind){
                value += card.getYanivValue();
            }
            if(value>=hand.peek().getYanivValue()){
                discardedCards.addAll(fourOfAKind);
            }else{
            discardedCards.add(hand.peek());
            }
        }else if (HandleCombination.hasThreeOfAKind(hand)){
            Deque<Card> threeOfAKind = ThreeOfAKind.getThreeOfAKind(hand);
            for (Card card : threeOfAKind){
                value += card.getYanivValue();
            }
            if(value>=hand.peek().getYanivValue()){               // faut gerer qd ils ont 2 combi a la fois
                discardedCards.addAll(threeOfAKind);
            }else{
            discardedCards.add(hand.peek());
            }
        }else if(HandleCombination.hasPair(hand)){
            Deque<Card> pair = Pair.getHighestPair(hand);
            for (Card card : pair){
                value += card.getYanivValue();
            }
            if(value>=hand.peek().getYanivValue()){
                discardedCards.addAll(pair);
            }else{
            discardedCards.add(hand.peek());
            }
        }else if(HandleCombination.hasSuite(hand)){
            Deque<Card> suit = Suite.getSuite(hand);
            for (Card card : suit){
                value += card.getYanivValue();
            }
            if(value>=hand.peek().getYanivValue()){
                discardedCards.addAll(suit);
            }else{
            discardedCards.add(hand.peek());
            }
        }else{
            discardedCards.add(hand.peek());
        }

        
        return discardedCards;
    }

    

    @Override
    public void discard(DiscardPile discardPile) {
        // TODO Auto-generated method stub
        super.discard(discardPile);
    }

    @Override
    public void play(DiscardPile discardPile, Deck deck, PriorityQueue<Card> hand) {
        Card lastCardDiscarded = discardPile.getDiscardPile().peekLast();
        discard(discardPile);
        if((lastCardDiscarded.getYanivValue()<7) || (hand.contains(lastCardDiscarded))){
            drawFromDiscardPile(lastCardDiscarded);
            System.out.println(getName()+ " draws "+lastCardDiscarded.getSuit() + "-" + lastCardDiscarded.getRank()+ " from DiscardedPile.");
        } else{
            drawFromDeck(deck, discardPile);
        }
    }

  
    
}
