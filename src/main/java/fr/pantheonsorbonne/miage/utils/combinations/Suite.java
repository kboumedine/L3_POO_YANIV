package fr.pantheonsorbonne.miage.utils.combinations;

import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import fr.pantheonsorbonne.miage.utils.Card;
import fr.pantheonsorbonne.miage.utils.Card.Rank;
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



    public static Deque<Card> getHighestSuit(PriorityQueue<Card> hand) {
        // Utiliser une Map pour suivre la suite de plus grande valeur pour chaque motif
        Map<Suit, Deque<Card>> highestSuitMap = new HashMap<>();

        for (Card card : hand) {
            Suit suit = card.getSuit();

            // Si une suite de plus grande valeur existe déjà pour le motif, comparer les valeurs
            if (highestSuitMap.containsKey(suit)) {
                Deque<Card> currentHighestSuite = highestSuitMap.get(suit);
                if (card.getRank().getValue() > currentHighestSuite.getLast().getRank().getValue()) {
                    // Remplacer la suite actuelle par la nouvelle suite
                    highestSuitMap.put(suit, new LinkedList<>(currentHighestSuite));
                    highestSuitMap.get(suit).add(card);
                }
            } else {
                // Si aucune suite n'a été enregistrée pour le motif, ajouter la carte actuelle comme début de la suite
                highestSuitMap.put(suit, new LinkedList<>(List.of(card)));
            }
        }

        // Trouver la suite de plus grande valeur parmi toutes les suites
        Deque<Card> highestSuite = new LinkedList<>();
        for (Deque<Card> suite : highestSuitMap.values()) {
            if (suite.size() > highestSuite.size()) {
                highestSuite = suite;
            }
        }

        return highestSuite;
    }

    public static boolean isSuite(Deque<Card> discardedSuit, HashSet<Card> targetSuite) {
        // Convertir les deux Deques en ensembles pour faciliter la comparaison
        Set<Card> handSet = new HashSet<>(discardedSuit);

        // Vérifier si les ensembles sont égaux
        return handSet.equals(targetSuite);
    }
    
    public static boolean isSpecificSuite(Deque<Card> discardedSuit) {
        // Vérifier si la main contient la suite spécifique
        Set<Card> targetSuite = new HashSet<>(Arrays.asList(
                new Card(Suit.HEARTS, Rank.QUEEN),
                new Card(Suit.HEARTS, Rank.JACK),
                new Card(Suit.HEARTS, Rank.TEN),
                new Card(Suit.DIAMONDS, Rank.QUEEN),
                new Card(Suit.DIAMONDS, Rank.JACK),
                new Card(Suit.DIAMONDS, Rank.TEN),
                new Card(Suit.CLUBS, Rank.QUEEN),
                new Card(Suit.CLUBS, Rank.JACK),
                new Card(Suit.CLUBS, Rank.TEN),
                new Card(Suit.SPADES, Rank.QUEEN),
                new Card(Suit.SPADES, Rank.JACK),
                new Card(Suit.SPADES, Rank.TEN)
        ));

        return discardedSuit.containsAll(targetSuite);
    }

}
