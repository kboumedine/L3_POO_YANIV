package fr.pantheonsorbonne.miage.utils.net;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import fr.pantheonsorbonne.miage.utils.Card;
import fr.pantheonsorbonne.miage.utils.Deck;

public abstract class NetworkGame {

    private Deck deck = new Deck();
    private String hand="";

    public void play() {

        deck.shuffleDeck();
    
        for (String playerName : getInitialPlayers()) {
           
            List<Card> cards = new ArrayList<>();
            for (int i = 0; i < 7; i++) {
                Card drawnCard = (deck.getDeck().pop());
                cards.add(drawnCard);
            }

            hand="";
            for (Card card : cards) {
                hand += card.getSuit() + "-" + card.getRank() + " ";
            }
        
            giveCardsToPlayer(playerName, hand);
            System.out.println();
            playRound(playerName, hand);
            getPoints(playerName, hand);
        }
      
        final Queue<String> players = new LinkedList<>();
        players.addAll(this.getInitialPlayers());

    }

    protected abstract void giveCardsToPlayer(String playerName, String hand);
    protected abstract List<String> getInitialPlayers();
    protected abstract void playRound(String playerName, String hand);
    protected abstract void getPoints(String playerName, String hand);


    public static int calculateYanivValue(String hand) {
        String[] cards = hand.split(" ");
        int yanivValue = 0;

        for (String cardString : cards) {
            Card card = createCardFromString(cardString);
            yanivValue += card.getYanivValue();
        }

        return yanivValue;
    }

    private static Card createCardFromString(String cardString) {
        String[] parts = cardString.split("-");
        Card.Suit suit = Card.Suit.valueOf(parts[0]);
        Card.Rank rank = Card.Rank.valueOf(parts[1]);
        return new Card(suit, rank);
    }


    public static String getCardDiscarded(String hand) {
       
        String[] elements = hand.split(" ");

        if (elements.length > 0) {
            return elements[elements.length - 1];
        } else {
        
            return "";
        }
    }

    public void discardCard(String hand, String cardDiscarded) {
      
        List<String> elements = Arrays.asList(hand.split(" "));

    
        elements.remove(cardDiscarded);

        hand = String.join(" ", elements);
    }
    
    
    
}
