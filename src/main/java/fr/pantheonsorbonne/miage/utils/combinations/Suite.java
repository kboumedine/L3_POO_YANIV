package fr.pantheonsorbonne.miage.utils.combinations;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;

import fr.pantheonsorbonne.miage.utils.Card;

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
    
    

}
