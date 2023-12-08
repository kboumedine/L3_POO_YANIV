package fr.pantheonsorbonne.miage.utils.net;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import fr.pantheonsorbonne.miage.Facade;
import fr.pantheonsorbonne.miage.HostFacade;
import fr.pantheonsorbonne.miage.model.GameCommand;
import fr.pantheonsorbonne.miage.utils.Deck;
import fr.pantheonsorbonne.miage.utils.DumbPlayer;
import fr.pantheonsorbonne.miage.utils.Engine;
import fr.pantheonsorbonne.miage.utils.Player;
import fr.pantheonsorbonne.miage.utils.SmartPlayer;



public class NetworkHost extends Engine{

    private static final int PLAYER_COUNT = 3;
    private final HostFacade hostFacade;
    private static Deck deck;
    private static LinkedList<Player>players;
    private final fr.pantheonsorbonne.miage.model.Game yaniv;


    

    public NetworkHost(HostFacade hostFacade, LinkedList<Player>players, Deck deck,
            fr.pantheonsorbonne.miage.model.Game yaniv) {
        NetworkHost.players = players;
        this.hostFacade = hostFacade;
        NetworkHost.deck = deck;
        this.yaniv = yaniv;
    }




    public static void main(String[] args) {
        //create the host facade
        HostFacade hostFacade = Facade.getFacade();
        hostFacade.waitReady();

        //set the name of the player
        hostFacade.createNewPlayer("Host");

        //create a new game of war
        fr.pantheonsorbonne.miage.model.Game yaniv = hostFacade.createNewGame("YANIV");

        //wait for enough players to join
        hostFacade.waitForExtraPlayerCount(PLAYER_COUNT);

        Engine host = new NetworkHost(hostFacade, players, deck, yaniv);
        host.playRound();
        System.exit(0);

    }
    





    @Override
    protected List<Player> initPlayers() {
        players = new LinkedList<>();
        // Ajoutez ici la logique pour créer et ajouter les joueurs à la liste 'players'
        // Par exemple, si vous avez des objets Player déjà créés, vous pouvez les ajouter à la liste ici.

        // Exemple avec deux joueurs factices pour démarrer
        players.add(new SmartPlayer("Player1"));
        players.add(new DumbPlayer("Player2"));

        return players;
    }



    @Override
    protected void eliminatePlayers() {

        Iterator<Player> iterator = players.iterator();

        while (iterator.hasNext()) {
            Player player = iterator.next();

            if (player.totalPoint > 100) {
                System.out.println("Eliminating player: " + player.getName());
                iterator.remove(); // Supprime le joueur de la liste
            }
        }
        
        hostFacade.sendGameCommandToPlayer(yaniv, "eliminate", new GameCommand("Eliminate a player"));
    }


   
}
