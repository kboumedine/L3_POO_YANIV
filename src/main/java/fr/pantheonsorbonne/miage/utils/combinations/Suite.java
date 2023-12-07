package fr.pantheonsorbonne.miage.utils.combinations;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import fr.pantheonsorbonne.miage.utils.Card;
import fr.pantheonsorbonne.miage.utils.Card.Suit;

public class Suite implements Combination {

    @Override
    public boolean isContainedIn(PriorityQueue<Card> hand) {
        // Utiliser une Map pour suivre les valeurs uniques pour chaque motif
        Map<Suit, Set<Integer>> suitValuesMap = new HashMap<>();

        for (Card card : hand) {
            Suit suit = card.getSuit();
            int value = card.getRank().getValue();

            // Si la valeur est déjà présente pour le motif, pas besoin de la vérifier à nouveau
            if (suitValuesMap.containsKey(suit) && suitValuesMap.get(suit).contains(value)) {
                continue;
            }

            // Vérifier si une suite d'au moins trois cartes est présente pour le motif donné
            Set<Integer> suitValues = suitValuesMap.computeIfAbsent(suit, k -> new HashSet<>());
            if (suitValues.contains(value - 1) || suitValues.contains(value + 1)) {
                return true;
            }

            suitValues.add(value);
        }

        // Aucune suite d'au moins trois cartes trouvée
        return false;
    }



    public static Deque<Card> getSuite(PriorityQueue<Card> hand) {
        return new ArrayDeque<>(hand);
    }

    public static boolean isSuitDiscarded(Deque<Card> discardedCards){
        
        if (discardedCards.size() < 2) {
            // Une pile de moins de 2 cartes ne peut pas former une suite
            return false;
        }

        // Vérification de la suite dans la pile de défausse
        while (discardedCards.size() > 1) {
            Card currentCard = discardedCards.poll();
            Card nextCard = discardedCards.peek();

            if (nextCard.getRank().getValue() != currentCard.getRank().getValue() + 1) {
                return false;
            }
        }

        return true;
    }
}
