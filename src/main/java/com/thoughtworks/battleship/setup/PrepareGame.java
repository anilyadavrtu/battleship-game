package com.thoughtworks.battleship.setup;

import java.util.List;

public class PrepareGame {
    /**
     * if input is valid start the game
     *
     * @param game
     */
    public void playGame(Ship game) {
        if (game.inputValid) {
            int playerNum;

            // Create board and add ships for player 1
            playerNum = 1;
            Player player1 = new Player(game.boardWidth, game.boardHeight);
            player1.addShips(game.numberOfShips, game.ships, playerNum);

            // create board and add ships for player 2
            playerNum = 2;
            Player player2 = new Player(game.boardWidth, game.boardHeight);
            player2.addShips(game.numberOfShips, game.ships, playerNum);

            // lists of coordinates for missiles fired by the players (indices to use with lists)
            List player1Missiles = game.player1Missiles;
            List player2Missiles = game.player2Missiles;
            int xCoord, yCoord;


            // lists of coordinates for missiles fired by the players (in letter number format, for printing)
            List p1Missiles = game.p1MissilesTarget;
            List p2Missiles = game.p2MissilesTarget;

            String status = "", missileTarget;

            // keep taking input from the missiles list until there is a winner
            // or until all missiles are fired by both users and no winner
            do {

                // start with player 1. while the missile hits a ship, keep firing missiles
                do {
                    // if list is empty then pass the turn to player 2
                    if (p1Missiles.isEmpty()) {
                        System.out.println("Player-1 has no more missiles left to launch");
                        status = "miss";
                    } else {
                        // x coordinate for missile
                        xCoord = (Integer) player1Missiles.get(0) - 1;
                        player1Missiles.remove(0);

                        // y coordinate for missile
                        yCoord = (Integer) player1Missiles.get(0) - 1;
                        player1Missiles.remove(0);

                        missileTarget = (String) p1Missiles.get(0);
                        p1Missiles.remove(0);

                        // player 2 checks if the missile coordinates hit one of the ships of player 2
                        // return hit, miss or won (if all ships are destroyed)
                        status = player2.checkCoordinates(xCoord, yCoord, missileTarget);
                    }

                } while (status.equals("hit"));


                do {
                    if (p2Missiles.isEmpty()) {
                        System.out.println("Player-2 has no more missiles left to launch");
                        status = "miss";
                    } else {
                        xCoord = (Integer) player2Missiles.get(0) - 1;
                        player2Missiles.remove(0);

                        yCoord = (Integer) player2Missiles.get(0) - 1;
                        player2Missiles.remove(0);

                        missileTarget = (String) p2Missiles.get(0);
                        p2Missiles.remove(0);

                        status = player1.checkCoordinates(xCoord, yCoord, missileTarget);
                    }

                } while (status.equals("hit"));

                // if both lists are empty then no winner, break.
                if (p1Missiles.isEmpty() && p2Missiles.isEmpty()) {
                    System.out.println("No winner");
                    break;
                }

            } while (!status.equals("won"));
        }
    }
}
