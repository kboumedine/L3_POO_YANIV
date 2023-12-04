package fr.pantheonsorbonne.miage.utils;



public class Game {

    public static void main (String[] args){
        initGame game = new initGame();
        game.launchGame();
        game.playRound();
        while(game.players.size()>1){
            game.newRound();
            game.playRound();
        }
    }
}


