package com.thoughtworks.battleship.setup;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;
/**
 * @author anil
 * Player
 */
public class Player {

    List<List> board;
    int boardWidth;
    int boardHeight;
    int playerNumber;
    /**
     * spaces taken by ships on the board
     */
    int areaOfShips = 0;
    /**
     * used in calculating the number of hits to ships
     */
    int counter = 0;

    /**
     * create board with width and height
     *
     * @param bWidth
     * @param bHeight
     */
    public Player(int bWidth, int bHeight) {
        boardWidth = bWidth;
        boardHeight = bHeight;
        board = new ArrayList();

        for (int i = 0; i < bHeight; i++) {
            ArrayList<Object> row = new ArrayList();
            for (int j = 0; j < bWidth; j++) {
                row.add('-');
            }
            board.add(row);
        }
    }

    /**
     * @param numberOfShips
     * @param ships
     * @param playerNum
     */
    public void addShips(int numberOfShips, List[] ships, int playerNum) {

        playerNumber = playerNum;
        // for every ship get info to populate the board
        for (int i = 0; i < numberOfShips; i++) {
            List ship = ships[i];

            char shipName = (Character) (ship.get(0));
            int shipWidth = (Integer) ship.get(1);
            int shipHeight = (Integer) ship.get(2);
            int shipXcoordinate;
            int shipYcoordinates;
            if (playerNum == 1) {
                shipXcoordinate = (Integer) ship.get(3);
                shipYcoordinates = (Integer) ship.get(4);
            } else {
                shipXcoordinate = (Integer) ship.get(5);
                shipYcoordinates = (Integer) ship.get(6);
            }

            int startXindex = shipXcoordinate - 1;
            int endXindex = startXindex + shipWidth - 1;

            int startYindex = shipYcoordinates - 1;
            int endYindex = startYindex + shipHeight - 1;

            // add the ship in corresponding locations on board
            for (int j = startYindex; j <= endYindex; j++) {
                for (int k = startXindex; k <= endXindex; k++) {
                    board.get(j).set(k, shipName);
                    areaOfShips++;
                }
            }

        }

        /*
         * printing the board for visual representation
         */
        out.println();
        print();
        out.println(" Player " + playerNumber + " board");
        print();
        out.print("  ");
        for (int i = 1; i <= boardWidth; i++) {
            if (i == boardWidth) {
                out.println(" " + i);
                break;
            }
            out.print(" " + i + ",");
        }

        for (int i = 0; i < boardHeight; i++) {
            char upper = (char) ('A' + i);
            out.println(upper + " " + board.get(i));
        }
        print();
        out.println();
    }

    /**
     * function that checks where the missile lands giving its coordinates
     *
     * @param xCoord
     * @param yCoord
     * @param missileTarget
     * @return
     */
    public String checkCoordinates(int xCoord, int yCoord, String missileTarget) {
        // to indicate which player is firing the missile
        int player;
        if (playerNumber == 1) {
            player = 2;
        } else {
            player = 1;
        }

        // get the type of ship at the location
        char shipType = (Character) board.get(yCoord).get(xCoord);

        // if the ship is of type P then destroy it, increment hit
        // counter,change the type on the board, and return hit.
        if (shipType == 'P') {

            counter++;
            if (counter == areaOfShips) {
                out.println(
                        "Player-" + player + " fires a missile with target " + missileTarget + " which got hit");
                out.println("Player-" + player + " won the battle");
                return "won";
            }
            board.get(yCoord).set(xCoord, '-');
            out.println("Player-" + player + " fires a missile with target " + missileTarget + " which got hit");
            return "hit";
        }

        // if ship is type Q then change type on board to P to indicate it needs
        // one more missile to be destroyed, return hit.
        else if (shipType == 'Q') {
            board.get(yCoord).set(xCoord, 'P');
            out.println("Player-" + player + " fires a missile with target " + missileTarget + " which got hit");
            return "hit";
        }

        // if no ship hit , return miss
        out.println("Player-" + player + " fires a missile with target " + missileTarget + " which got miss");
        return "miss";
    }

   public void print(){
       out.println("------------------");
    }
}
