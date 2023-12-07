package fr.pantheonsorbonne.miage.game;


import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.utils.Card;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardTest {


    @Test
    void testGettersAndSetters() {
        Card card = new Card(Card.Suit.DIAMONDS, Card.Rank.KING);

        assertEquals(Card.Suit.DIAMONDS, card.getSuit());
        assertEquals(Card.Rank.KING, card.getRank());

        card.setSuit(Card.Suit.SPADES);
        card.setRank(Card.Rank.SEVEN);

        assertEquals(Card.Suit.SPADES, card.getSuit());
        assertEquals(Card.Rank.SEVEN, card.getRank());
    }


    @Test
    void testGetYanivValue() {
        Card card = new Card(Card.Suit.HEARTS, Card.Rank.TEN);
        assertEquals(10, card.getYanivValue());
    }
 
    @Test
    void testCompareTo() {
        Card card1 = new Card(Card.Suit.HEARTS, Card.Rank.ACE);
        Card card2 = new Card(Card.Suit.DIAMONDS, Card.Rank.TWO);
        Card card3 = new Card(Card.Suit.CLUBS, Card.Rank.JACK);

        // Test when card1 has a higher value than card2
        assertEquals(1, card1.compareTo(card2));

        // Test when card2 has a lower value than card1
        assertEquals(-1, card2.compareTo(card1));

        // Test when card1 and card3 have the same value
        assertEquals(0, card1.compareTo(card3));
    }  


}
