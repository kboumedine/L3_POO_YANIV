package fr.pantheonsorbonne.miage.utils.net;

import java.util.ArrayList;
import java.util.List;

import fr.pantheonsorbonne.miage.Facade;
import fr.pantheonsorbonne.miage.HostFacade;
import fr.pantheonsorbonne.miage.model.Game;
import fr.pantheonsorbonne.miage.model.GameCommand;


public class NetworkHost extends NetworkGame{

    private static final int PLAYER_COUNT = 3;
    private Game yaniv;
    private final HostFacade hostFacade;


    public NetworkHost(HostFacade hostFacade, Game yaniv) {
        this.hostFacade = hostFacade;
        this.yaniv = yaniv;
    }

    public static void main(String[] args) {

        HostFacade hostFacade = Facade.getFacade();
        hostFacade.waitReady();

        hostFacade.createNewPlayer("Host");

        fr.pantheonsorbonne.miage.model.Game yaniv = hostFacade.createNewGame("YANIV");

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
    protected void playRound(String playerName, String hand) {

        String card = getCardDiscarded(hand);
        hostFacade.sendGameCommandToPlayer(yaniv, playerName, new GameCommand("cardToDiscard", card));       
        
    }

    @Override
    protected void getPoints(String playerName, String hand) {
        
        int points = calculateYanivValue(hand);
        hostFacade.sendGameCommandToPlayer(yaniv, playerName, new GameCommand("getPoints", String.valueOf(points)));
    }



   
}
