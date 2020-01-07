package com.thoughtworks.battleship.setup;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * @author anil
 * Ship
 */
public class Ship {

    public boolean inputValid = false;

    Scanner scanner;
    String readLine;

    public int boardWidth;
    public int boardHeight;
    public int numberOfShips;
    public List ships[];
    public List player1Missiles;
    public List player2Missiles;
    public List p1MissilesTarget;
    public List p2MissilesTarget;


    /**
     * getInput
     */
    public void getInput() {

        System.out.println("Please enter input sample:");
        getBoardDimensions();
    }


    /**
     * read the first line that is dimensions of the board
     */
    private void getBoardDimensions() {

        do {
            scanner = new Scanner(System.in);
            readLine = scanner.nextLine();

        } while (readLine.equals(""));

        parseBoardDimensions(readLine);
    }


    /**
     * @param readLine check the dimensions ( width: 1-9 , height: a-z)
     */
    public void parseBoardDimensions(String readLine) {
        String dimensions = readLine;
        String[] parseDim = dimensions.split(" ");

        if (parseDim.length != 2) {
            System.out.println("Please enter valid dimensions");
            return;
        }

        try {
            boardWidth = Integer.valueOf(parseDim[0]);
        } catch (Exception e) {
            System.out.println("Please enter a valid number for width between 0 and 10");
            return;
        }
        boardHeight = Character.getNumericValue((parseDim[1]).charAt(0)) - 9;

        if (boardWidth < 1 || boardWidth > 9 || boardHeight < 1 || boardHeight > 26) {
            System.out.println("Board dimensions are not valid");
            return;
        }
        getNumberOfShips();
    }


    /**
     * read the second line, number of ships
     */
    private void getNumberOfShips() {

        do {
            scanner = new Scanner(System.in);
            readLine = scanner.nextLine();

        } while (readLine.equals(""));

        parseNumberOfShips(readLine);
    }

    /**
     * check the number of ships (number: 1-width*height)
     *
     * @param readLine
     */
    public void parseNumberOfShips(String readLine) {

        try {
            numberOfShips = Integer.valueOf(readLine);
        } catch (Exception e) {
            System.out.println("Please enter a valid number of ships");
            return;
        }

        if ((numberOfShips > boardHeight * boardWidth) || (numberOfShips < 1)) {
            System.out.println("Number of ships is not valid");
            return;
        }

        getShipsInfo();
    }


    /**
     * read the n lines that have ships info
     */
    private void getShipsInfo() {
        String input[] = new String[numberOfShips];
        for (int i = 0; i < input.length; i++) {
            do {
                scanner = new Scanner(System.in);
                readLine = scanner.nextLine();
            } while (readLine.equals(""));
            input[i] = readLine;
        }

        parseShipInfo(input);
    }


    /**
     * parse and check ship info
     *
     * @param input
     */
    public void parseShipInfo(String[] input) {
        ships = new ArrayList[numberOfShips];

        for (int i = 0; i < numberOfShips; i++) {
            String ship = input[i];
            String[] shipInfo = ship.split(" ");

            if (shipInfo.length != 5) {
                System.out.println("Ship info is missing or not valid");
                return;
            }


            char shipName = shipInfo[0].charAt(0);
            if (shipName != 'Q' && shipName != 'P') {
                System.out.println("Ship name is not valid " + shipName);
                return;
            }

            int shipWidth;
            int shipHeight;
            try {
                shipWidth = Integer.valueOf(shipInfo[1]);
                shipHeight = Integer.valueOf(shipInfo[2]);
                if (shipWidth < 1 || shipWidth > boardWidth || shipHeight < 1 || shipHeight > boardHeight) {
                    System.out.println("Please enter valid ship dimensions");
                    return;
                }
            } catch (Exception e) {
                System.out.println("Please enter valid ship dimensions");
                return;
            }

            int player1ShipY = Character.getNumericValue((shipInfo[3]).charAt(0)) - 9;
            int player1ShipX = Character.getNumericValue((shipInfo[3]).charAt(1));

            int player2ShipY = Character.getNumericValue((shipInfo[4]).charAt(0)) - 9;
            int player2ShipX = Character.getNumericValue((shipInfo[4]).charAt(1));

            if (player1ShipX < 1 || player2ShipX < 1 || player1ShipX > boardWidth || player2ShipX > boardWidth ||
                    player1ShipY < 1 || player2ShipY < 1 || player1ShipY > boardHeight || player2ShipY > boardHeight) {

                System.out.println("Ship coordinates not valid");
                return;
            }

            List shipInformation = new ArrayList();
            shipInformation.add(shipName);
            shipInformation.add(shipWidth);
            shipInformation.add(shipHeight);
            shipInformation.add(player1ShipX);
            shipInformation.add(player1ShipY);
            shipInformation.add(player2ShipX);
            shipInformation.add(player2ShipY);

            ships[i] = shipInformation;
        }

        getPlayer1Missiles();

    }

    /**
     * read input of player 1 missiles
     */
    private void getPlayer1Missiles() {

        do {
            scanner = new Scanner(System.in);
            readLine = scanner.nextLine();
        } while (readLine.equals(""));

        parsePlayer1Missiles(readLine);
    }

    /**
     * parse and store the missiles
     *
     * @param readLine
     */
    public void parsePlayer1Missiles(String readLine) {
        String[] missiles = readLine.split(" ");

        player1Missiles = new ArrayList();
        p1MissilesTarget = new ArrayList();

        for (int i = 0; i < missiles.length; i++) {
            p1MissilesTarget.add(missiles[i]);
            int missileY = Character.getNumericValue((missiles[i]).charAt(0)) - 9;
            int missileX = Character.getNumericValue((missiles[i]).charAt(1));
            player1Missiles.add(missileX);
            player1Missiles.add(missileY);
        }

        getPlayer2Missiles();
    }

    /**
     * read input of player 2 missiles
     */
    private void getPlayer2Missiles() {

        do {
            scanner = new Scanner(System.in);
            readLine = scanner.nextLine();
        } while (readLine.equals(""));

        parsePlayer2Missiles(readLine);
    }

    /**
     * parse and store missiles
     *
     * @param readLine
     */
    public void parsePlayer2Missiles(String readLine) {
        String[] missiles = readLine.split(" ");

        player2Missiles = new ArrayList();
        p2MissilesTarget = new ArrayList();


        for (int i = 0; i < missiles.length; i++) {
            p2MissilesTarget.add(missiles[i]);
            int missileY = Character.getNumericValue((missiles[i]).charAt(0)) - 9;
            int missileX = Character.getNumericValue((missiles[i]).charAt(1));
            player2Missiles.add(missileX);
            player2Missiles.add(missileY);
        }

        inputValid = true;
    }


}
