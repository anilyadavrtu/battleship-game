package com.thoughtworks.battleship.play;

import com.thoughtworks.battleship.setup.Ship;
import com.thoughtworks.battleship.setup.PrepareGame;

/**
 * @author anil
 * PlayGame
 */
public class PlayGame {
    /**
     * start program by reading and parsing input from user
     *
     * @param args
     */
    public static void main(String[] args) {
        Ship ship = new Ship();
        PrepareGame prepareGame=new PrepareGame();
        ship.getInput();
        prepareGame.playGame(ship);
    }

}
