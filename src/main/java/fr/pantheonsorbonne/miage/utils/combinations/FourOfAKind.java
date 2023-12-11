package fr.pantheonsorbonne.miage.utils.combinations;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;

import fr.pantheonsorbonne.miage.utils.Card;
import fr.pantheonsorbonne.miage.utils.Card.Rank;

public class FourOfAKind implements Combination {

    @Override
    public boolean isContainedIn(PriorityQueue<Card> hand) {
        
        Map<Rank, Integer> rankCount = new HashMap<>();
        for (Card card : hand) {
            rankCount.put(card.getRank(), rankCount.getOrDefault(card.getRank(), 0) + 1);
        }

      
        for (int count : rankCount.values()) {
            if (count >= 4) {
                return true;
            }
        }

        return false;
    }

    public static Deque<Card> getFourOfAKind(PriorityQueue<Card> cards) {
        Map<Card.Rank, Deque<Card>> rankMap = new HashMap<>();

      
        for (Card card : cards) {
            rankMap.computeIfAbsent(card.getRank(), k -> new LinkedList<>()).add(card);
        }

        Deque<Card> fourOfAKind = null;
        for (Deque<Card> cardDeque : rankMap.values()) {
            if (cardDeque.size() == 4) {
                fourOfAKind = cardDeque;
                break; 
            }
        }

        return fourOfAKind;
    }
}
    
