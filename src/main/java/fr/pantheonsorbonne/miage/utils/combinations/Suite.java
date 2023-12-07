package fr.pantheonsorbonne.miage.utils.combinations;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import fr.pantheonsorbonne.miage.utils.Card;
import fr.pantheonsorbonne.miage.utils.Card.Suit;

public class Suite implements Combination {

    @Override
    public boolean isContainedIn(PriorityQueue<Card> hand) {
        if (hand.size() < 3) {
            // Une suite doit avoir au moins 3 cartes
            return false;
        }

        List<Card> cardList = new ArrayList<>(hand);
        Collections.sort(cardList);

        for (int i = 0; i < cardList.size() - 2; i++) {
            if (isSequence(cardList.subList(i, i + 3))) {
                return true;
            }
        }

        return false;
    }

    private static boolean isSequence(List<Card> cards) {
        for (int i = 1; i < cards.size(); i++) {
            if (cards.get(i).getSuit() != cards.get(0).getSuit() ||
                    Math.abs(cards.get(i).getRank().ordinal() - cards.get(i - 1).getRank().ordinal()) != 1) {
                return false;
            }
        }
        return true;
    }


    public static Deque<Card> getSuit(PriorityQueue<Card> hand) {
        if (hand.size() < 3) {
            // Une suite doit avoir au moins 3 cartes
            throw new IllegalArgumentException("La main doit contenir au moins 3 cartes.");
        }

        List<Card> cardList = new ArrayList<>(hand);
        Collections.sort(cardList);

        for (int i = 0; i < cardList.size() - 2; i++) {
            if (isSequence(cardList.subList(i, i + 3))) {
                return new ArrayDeque<>(cardList.subList(i, i + 3));
            }
        }

        throw new IllegalStateException("Aucune suite trouvée dans la main.");
    }
    
    public static boolean hasTenJackQueenSequence(Deque<Card> cards) {
        List<Card> cardList = new ArrayList<>(cards);

        for (int i = 0; i <= cardList.size() - 3; i++) {
            List<Card> subList = new ArrayList<>(cardList.subList(i, i + 3));
            Collections.sort(subList);

            if (isTenJackQueenSequence(subList)) {
                return true;
            }
        }

        return false;
    }

    private static boolean isTenJackQueenSequence(List<Card> cards) {
        return cards.get(0).getRank() == Card.Rank.TEN &&
               cards.get(1).getRank() == Card.Rank.JACK &&
               cards.get(2).getRank() == Card.Rank.QUEEN &&
               cards.get(0).getSuit() == cards.get(1).getSuit() &&
               cards.get(1).getSuit() == cards.get(2).getSuit();
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
    

}
