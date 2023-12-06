package fr.pantheonsorbonne.miage.utils.specialrules;
import fr.pantheonsorbonne.miage.utils.Player;

public interface SpecialRules {

    boolean changeTurn(Player player);
    boolean shouldSkipNextTurn(Player player);
    void drawAndExchangeFromOtherPlayer();
    void finishTheSuitOrDraw();

}


    
    

