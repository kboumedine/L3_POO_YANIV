package fr.pantheonsorbonne.miage.utils.combinations;


import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;

import java.util.Map;
import java.util.PriorityQueue;

import fr.pantheonsorbonne.miage.utils.Card;
import fr.pantheonsorbonne.miage.utils.Card.Rank;

public class Pair implements Combination{

    @Override
    public boolean isContainedIn(PriorityQueue<Card> hand) {
        // Utilisation d'une map pour compter le nombre de cartes de chaque valeur
        Map<Rank, Integer> rankCount = new HashMap<>();
        for (Card card : hand) {
            rankCount.put(card.getRank(), rankCount.getOrDefault(card.getRank(), 0) + 1);
        }

        // Vérification de la paire
        for (int count : rankCount.values()) {
            if (count >= 2) {
                return true;
            }
        }

        return false;
    }

    public static Deque<Card> getHighestPair(PriorityQueue<Card> cards) {
        Map<Card.Rank, Deque<Card>> rankMap = new HashMap<>();

        // Regrouper les cartes par valeur
        for (Card card : cards) {
            rankMap.computeIfAbsent(card.getRank(), k -> new LinkedList<>()).add(card);
        }

        // Rechercher la paire de valeur la plus élevée
        Deque<Card> highestPair = null;
        for (Deque<Card> cardDeque : rankMap.values()) {
            if (cardDeque.size() == 2 && (highestPair == null || cardDeque.getFirst().getRank().compareTo(highestPair.getFirst().getRank()) > 0)) {
                highestPair = cardDeque;
            }
        }

        return highestPair;
    }

    public static boolean hasPairOf(Deque<Card> cards, int targetRank) {
        int count = 0;
        for (Card card : cards) {
            if (card.getYanivValue() == targetRank) {
                count++;
            }
            if (count == 2) {
                    return true;
            }
        }
        return false;
    }
    
}
    

