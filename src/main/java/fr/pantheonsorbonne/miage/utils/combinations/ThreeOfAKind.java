package fr.pantheonsorbonne.miage.utils.combinations;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;

import fr.pantheonsorbonne.miage.utils.Card;
import fr.pantheonsorbonne.miage.utils.Card.Rank;

public class ThreeOfAKind implements Combination {

    @Override
    public boolean isContainedIn(PriorityQueue<Card> hand) {
        
        Map<Rank, Integer> rankCount = new HashMap<>();
        for (Card card : hand) {
            rankCount.put(card.getRank(), rankCount.getOrDefault(card.getRank(), 0) + 1);
        }

        for (Map.Entry<Rank, Integer> entry : rankCount.entrySet()) {
            if (entry.getValue() >= 3) {
                return true;
            }
        }

        return false;
    }

    public static Deque<Card> getThreeOfAKind(PriorityQueue<Card> cards) {
        Map<Card.Rank, Deque<Card>> rankMap = new HashMap<>();

        for (Card card : cards) {
            rankMap.computeIfAbsent(card.getRank(), k -> new LinkedList<>()).add(card);
        }

        Deque<Card> threeOfAKind = null;
        for (Deque<Card> cardDeque : rankMap.values()) {
            if (cardDeque.size() == 3) {
                threeOfAKind = cardDeque;
                break; 
            }
        }

        return threeOfAKind;
    }
    
}
