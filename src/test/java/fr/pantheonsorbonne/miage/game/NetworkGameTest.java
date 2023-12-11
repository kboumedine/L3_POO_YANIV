package fr.pantheonsorbonne.miage.game;

import static org.junit.jupiter.api.Assertions.assertEquals;


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

}
