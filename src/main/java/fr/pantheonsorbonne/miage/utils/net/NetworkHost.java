package fr.pantheonsorbonne.miage.utils.net;

import java.util.ArrayList;
import java.util.List;

import fr.pantheonsorbonne.miage.Facade;
import fr.pantheonsorbonne.miage.HostFacade;
import fr.pantheonsorbonne.miage.PlayerFacade;
import fr.pantheonsorbonne.miage.model.Game;
import fr.pantheonsorbonne.miage.model.GameCommand;
import fr.pantheonsorbonne.miage.utils.Card;
import fr.pantheonsorbonne.miage.utils.Deck;


public class NetworkHost extends NetworkGame{

    private static final int PLAYER_COUNT = 3;
    //private static Deck deck = new Deck();
    private Game yaniv;
    private final HostFacade hostFacade;
    private static Deck deck = new Deck();


    public NetworkHost(HostFacade hostFacade, Game yaniv) {
        this.hostFacade = hostFacade;
        this.yaniv = yaniv;
    }

    public static void main(String[] args) {

        PlayerFacade playerFacade = Facade.getFacade();
        //create the host facade
        HostFacade hostFacade = Facade.getFacade();
        hostFacade.waitReady();

        //set the name of the player
        hostFacade.createNewPlayer("Host");

        //create a new game of war
        fr.pantheonsorbonne.miage.model.Game yaniv = hostFacade.createNewGame("YANIV");

        //wait for enough players to join
        hostFacade.waitForExtraPlayerCount(PLAYER_COUNT);
        NetworkGame host = new NetworkHost(hostFacade, yaniv);
        host.play();

    }

    @Override
    protected List<String> getInitialPlayers() {
        return new ArrayList<>(this.yaniv.getPlayers());
    }

    @Override
    protected void giveCardsToPlayer(String playerName, String hand) {
        hostFacade.sendGameCommandToPlayer(yaniv, playerName, new GameCommand("cardsForYou", hand));
    }

    @Override
    public void declareWinner(String winner) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'declareWinner'");
    }

    @Override
    protected void playRound() {

        List<String> players = getInitialPlayers();

        for(;;){
            for (int i=0; i<players.size(); i++) {

                List<Card> cards = new ArrayList<>();
                for (int j = 0; j < 7; j++) {
                    Card drawnCard = (deck.getDeck().pop());
                    cards.add(drawnCard);
                }

                String hand = "";
                for (Card card : cards) {
                    hand += card.getSuit() + "-" + card.getRank() + " ";
                }

                String player = players.get(i);
                discardCard(hand,getCardDiscarded(hand));
                

            }
        }
    }



   
}
