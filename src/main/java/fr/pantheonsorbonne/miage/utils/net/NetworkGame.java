package fr.pantheonsorbonne.miage.utils.net;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import fr.pantheonsorbonne.miage.utils.Card;
import fr.pantheonsorbonne.miage.utils.Card.Rank;
import fr.pantheonsorbonne.miage.utils.Card.Suit;
import fr.pantheonsorbonne.miage.utils.Deck;

public abstract class NetworkGame {

    private Deck deck = new Deck();

    public void play() {

        deck.shuffleDeck();
        //send the initial hand to every players
        for (String playerName : getInitialPlayers()) {
            //get random cards
            List<Card> cards = new ArrayList<>();
            for (int i = 0; i < 7; i++) {
                Card drawnCard = (deck.getDeck().pop());
                cards.add(drawnCard);
            }

            String hand = "";
            for (Card card : cards) {
                hand += card.getSuit() + "-" + card.getRank() + " ";
            }
            //send them to this players
            giveCardsToPlayer(playerName, hand);
        }
        // make a queue with all the players
        final Queue<String> players = new LinkedList<>();
        players.addAll(this.getInitialPlayers());
        //repeat until only 1 player is left
        /*while (players.size() > 1) {
            
            for (String player : players){


            }

        }
        //since we've left the loop, we have only 1 player left: the winner
        String winner = players.poll();
        //send him the gameover and leave
        declareWinner(winner);
        System.out.println(winner + " won! bye");*/

    }

    protected abstract void giveCardsToPlayer(String playerName, String hand);
    protected abstract List<String> getInitialPlayers();
    public abstract void declareWinner(String winner);
    protected abstract void playRound();

    public static Card createCardFromString(String cardString) {
        String[] parts = cardString.split("-");
        
        if (parts.length != 2) {
            throw new IllegalArgumentException("La chaîne de carte est invalide : " + cardString);
        }

        String suitStr = parts[0];
        String rankStr = parts[1];

        Suit suit = Suit.valueOf(suitStr.toUpperCase());
        Rank rank = Rank.valueOf(rankStr.toUpperCase());

        // Créer et retourner la carte
        return new Card(suit, rank);
    }


    public static String getCardDiscarded(String hand) {
        // Diviser la chaîne en éléments individuels
        String[] elements = hand.split(" ");

        // Récupérer le dernier élément
        if (elements.length > 0) {
            return elements[elements.length - 1];
        } else {
            // Retourner une chaîne vide si la chaîne est vide
            return "";
        }
    }

    public void discardCard(String hand, String cardDiscarded) {
        // Convertir la chaîne de main en une liste d'éléments
        List<String> elements = Arrays.asList(hand.split(" "));

        // Retirer la carte de la main
        elements.remove(cardDiscarded);

        // Mettre à jour la main
        hand = String.join(" ", elements);
    }
    
    
    
}
