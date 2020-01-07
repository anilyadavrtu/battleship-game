package com.thoughtworks.battleship.setup;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

/**
 * @author anil
 */
public class PrepareGameTest {

    int playerNum;
    Player player1, player2;
    int boardWidth, boardHeight;
    int numberOfShips;
    List[] ships;
    String hitStatus;

    int missileXcoord, missileYcoord;
    String missileCoord; // include the coordinates in string format like 'B2'
    // where B is the second and 2 is the second column.

    // this case is after parsing the input from console and just to check
    // if coordinates of missile would hit or miss that of a ship on board
    @Before
    public void setUpGame() {

        boardWidth = 5;
        boardHeight = 5;
        numberOfShips = 1; // will add just one ship now

        ships = new ArrayList[numberOfShips];

        List ship = new ArrayList();

        char shipType = 'P';

        int shipWidth = 2;
        int shipHeight = 1;

        int shipXcordPl1 = 1;
        int shipYcordPl1 = 1;

        int shipXcordPl2 = 3;
        int shipYcordPl2 = 2;

        ship.add(shipType);
        ship.add(shipWidth);
        ship.add(shipHeight);
        ship.add(shipXcordPl1);
        ship.add(shipYcordPl1);
        ship.add(shipXcordPl2);
        ship.add(shipYcordPl2);

        ships[0] = ship;

        playerNum = 1;
        player1 = new Player(boardWidth, boardHeight);
        player1.addShips(numberOfShips, ships, playerNum);

        // create board and add ships for player 2
        playerNum = 2;
        player2 = new Player(boardWidth, boardHeight);
        player2.addShips(numberOfShips, ships, playerNum);
    }

    @Test
    public void testMissileHit() {
        setUpGame();

        // ships are represented in arraylists which are zero based
        // so first location will be (0,0) not (1,1).
        // this conversion is handled in the code
        missileXcoord = 0;
        missileYcoord = 0;

        missileCoord = "A1"; // where A1 corresponds to coordinates if we want
        // to hit a ship at (1,1)

        // if player2 fires then player1 checks if that matches ship coordinates
        // on the player's board
        // since we added a ship at coordinates (1,1) for player 1 we expect a
        // hit
        hitStatus = player1.checkCoordinates(missileXcoord, missileYcoord, missileCoord);
        Assert.assertEquals("hit", hitStatus); // true

        missileXcoord = 3;
        missileYcoord = 3;

        missileCoord = "D4"; // to correspond to (4,4)

        hitStatus = player1.checkCoordinates(missileXcoord, missileYcoord, missileCoord);
        // Assert.assertEquals("hit", hitStatus); // false

        //////////////////////////////////////////////////////////////////////////

        missileXcoord = 0;
        missileYcoord = 0;

        missileCoord = "A1"; // to correspond to (1,1)

        hitStatus = player2.checkCoordinates(missileXcoord, missileYcoord, missileCoord);
        // Assert.assertEquals("hit", hitStatus); // false

        missileXcoord = 2;
        missileYcoord = 1;

        missileCoord = "B3"; // to correspond to (2,1)

        hitStatus = player2.checkCoordinates(missileXcoord, missileYcoord, missileCoord);
        Assert.assertEquals("hit", hitStatus); // true

    }

}