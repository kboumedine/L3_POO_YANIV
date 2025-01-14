package fr.pantheonsorbonne.miage.utils.net;

import java.util.Random;

import fr.pantheonsorbonne.miage.Facade;
import fr.pantheonsorbonne.miage.PlayerFacade;
import fr.pantheonsorbonne.miage.model.GameCommand;


public class NetworkPlayer {

    static final String playerId = "Player-" + new Random().nextInt();
    static final PlayerFacade playerFacade = Facade.getFacade();
    static fr.pantheonsorbonne.miage.model.Game yaniv;


    public static void main(String[] args) {

        playerFacade.waitReady();
        playerFacade.createNewPlayer(playerId);
        yaniv = playerFacade.autoJoinGame("YANIV");

        while(true){
            GameCommand command = playerFacade.receiveGameCommand(yaniv);
                switch (command.name()) {
                    case "cardsForYou":
                        String myCards = command.body();
                        System.out.println("I have " + myCards);
                        break;
                    case "cardToDiscard":
                        String card = command.body();
                        System.out.println("I choose to discard "+ card);
                        break;
                    case "getPoints":
                        String points = command.body();
                        System.out.println("I have "+points+" points.");
                        break;
                }
        }
    }
    
}
