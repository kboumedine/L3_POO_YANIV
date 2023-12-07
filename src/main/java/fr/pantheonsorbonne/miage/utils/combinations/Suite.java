package fr.pantheonsorbonne.miage.utils.combinations;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

import fr.pantheonsorbonne.miage.utils.Card;

public class Suite implements Combination {

    @Override
    public boolean isContainedIn(PriorityQueue<Card> hand) {
        Deque<Card> suite = new ArrayDeque<>(hand);

        // Vérification de la suite
        while (suite.size() > 1) {
            Card currentCard = suite.poll();
            Card nextCard = suite.peek();

            if (nextCard.getRank().getValue() != currentCard.getRank().getValue() + 1) {
                return false;
            }
        }

        return true;
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
