package fr.pantheonsorbonne.miage.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.utils.net.NetworkGame;

public class NetworkGameTest {
    

    @Test
    void testCalculateYanivValue() {
        String hand = "HEARTS-KING HEARTS-SEVEN SPADES-FOUR";
        int yanivValue = NetworkGame.calculateYanivValue(hand);
        assertEquals(24, yanivValue, "Incorrect Yaniv value");
    }

    @Test
    void testGetCardDiscarded() {
        String hand = "HEARTS-KING HEARTS-SEVEN SPADES-FOUR";
        String cardDiscarded = NetworkGame.getCardDiscarded(hand);
        assertEquals("SPADES-FOUR", cardDiscarded, "Incorrect discarded card");
    }

    @Test
    void testDiscardCard() {
        String hand = "HEARTS-KING HEARTS-SEVEN SPADES-FOUR";
        String cardDiscarded = "SPADES-FOUR";

        NetworkGame networkGame = new NetworkGame() {
            @Override
            protected void giveCardsToPlayer(String playerName, String hand) {
            }

            @Override
            protected List<String> getInitialPlayers() {
                return null;
            }

            @Override
            protected void playRound(String playerName, String hand) {
            }

            @Override
            protected void getPoints(String playerName, String hand) {
            }
        };

        networkGame.discardCard(hand, cardDiscarded);

        // Verify that the card is removed
        assertTrue(!hand.contains(cardDiscarded), "Card should be discarded");
    }
}
