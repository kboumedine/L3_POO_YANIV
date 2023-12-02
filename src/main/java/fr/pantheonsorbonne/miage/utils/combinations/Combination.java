package fr.pantheonsorbonne.miage.utils.combinations;

import java.util.PriorityQueue;

import fr.pantheonsorbonne.miage.utils.Card;

public interface Combination {

    boolean isContainedIn(PriorityQueue<Card> hand);
    
}
