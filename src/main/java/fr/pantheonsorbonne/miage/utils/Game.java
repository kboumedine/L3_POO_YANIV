package fr.pantheonsorbonne.miage.utils;



public class Game {

    private Player gameWinner;

    public void playGame(){
        InitGame game = new InitGame();
        game.launchGame();
        game.playRound();
        while(game.players.size()>1){
            game.newRound();
            game.playRound();
        }

        gameWinner = game.players.peekFirst(); 
        System.out.println(gameWinner.getName()+" is the winner of the game !!!");
    }
}


