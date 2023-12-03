package fr.pantheonsorbonne.miage.utils;



public class Game {

    public static void main (String[] args){
        int nbRound=1;
        initGame game = new initGame();
        game.launchGame();
        game.playRound();
        while(nbRound++<3){
            game.newRound();
            game.playRound();
        }
    }
}


