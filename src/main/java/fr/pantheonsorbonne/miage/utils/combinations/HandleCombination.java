package fr.pantheonsorbonne.miage.utils.combinations;

import java.util.PriorityQueue;

import fr.pantheonsorbonne.miage.utils.Card;

public class HandleCombination {

    public static boolean hasSuite(PriorityQueue<Card> hand) {
        Combination suite = new Suite();
        return suite.isContainedIn(hand);
    }

    public static boolean hasPair(PriorityQueue<Card> hand) {
        Combination pair = new Pair();
        return pair.isContainedIn(hand);
    }

    public static boolean hasThreeOfAKind(PriorityQueue<Card> hand) {
        Combination threeOfAKind = new ThreeOfAKind();
        return threeOfAKind.isContainedIn(hand);
    }

    public static boolean hasFourOfAKind(PriorityQueue<Card> hand) {
        Combination fourOfAKind = new FourOfAKind();
        return fourOfAKind.isContainedIn(hand);
    }
    
}
