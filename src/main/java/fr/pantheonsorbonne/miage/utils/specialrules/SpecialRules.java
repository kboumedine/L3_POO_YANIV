package fr.pantheonsorbonne.miage.utils.specialrules;
import fr.pantheonsorbonne.miage.utils.Player;

public interface SpecialRules {

    boolean shouldReverseSense(Player player);
    boolean shouldSkipNextTurn(Player player);
    boolean shouldExchangeWithOtherPlayer(Player player);
    void exchangeWithOtherPlayer(Player player1, Player player2);
    void finishTheSuitOrDraw();

}


    
    

