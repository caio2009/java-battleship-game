package application;

import battleship.board.Position;
import battleship.ship.Ship;
import battleship.ship.ShipPosition;
import battleship.ship.ShipType;
import battleship.board.BattleshipBoard;
import battleship.board.BattleshipBoardException;
import battleship.board.BattleshipPosition;
import engine.BattleshipGameException;
import engine.BattleshipGame;
import engine.Player;
import utils.IntegerUtil;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UI {

    public static final String SPACE = " ";

    public static void printHeader() {
        System.out.println("**********   BATTLESHIP   **********\n");
    }

    public static void printBattleshipBoard(BattleshipBoard board) {
        // Set offset to align letters row
        if (!IntegerUtil.isTwoDigit(board.getRows())) printOffset(2);
        else printOffset(3);

        printLettersRow(board.getColumns());

        for (int i = 0; i < board.getRows(); i++) {
            printRow(i + 1, board.getColumns(), board);
        }
        breakline();
    }

    public static Position readPlayerTargetPosition(Scanner sc) {
        try {
            System.out.print("Enter target position: ");
            String input = sc.next();

            char column = input.substring(0, 1).charAt(0);
            int row = Integer.parseInt(input.substring(1));

            return new BattleshipPosition(column, row).toPosition();
        }
        catch (BattleshipBoardException e) {
            // throw new BattleshipBoardException("Invalid position: " + e.getMessage());
            throw new BattleshipGameException("Invalid position. " + e.getMessage());
        }
        catch (NumberFormatException e) {
            throw new BattleshipGameException("Problem in reading target position.");
        }
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void printMessage(String msg) {
        if (!msg.equals("")) {
            System.out.println(msg + "\n");
        }
    }

    public static void readChooseShipPosition(Player player, Scanner sc) {
        try {
            ShipType type = player.getMissingShipType();

            System.out.println("Adding new ship: " + type);
            System.out.println();

            System.out.println("Choose Ship Direction:\n");
            System.out.println("1-Horizontal   2-Vertical\n");
            System.out.print("Direction: ");

            int direction = sc.nextInt();
            System.out.println();

            if (direction != 1 && direction != 2) {
                throw new BattleshipGameException("Invalid input " + direction + ".");
            }

            System.out.print("Enter the ship position: ");
            String strPosition = sc.next();

            char column = strPosition.substring(0, 1).charAt(0);
            int row = Integer.parseInt(strPosition.substring(1));

            Position position = new BattleshipPosition(column, row).toPosition();

            player.newShip(type, direction, position);
        }
        catch (InputMismatchException e) {
            sc.nextLine();
            throw new BattleshipGameException("Can't input text on direction input.");
        }
        catch (NumberFormatException e) {
            throw new BattleshipGameException("Problem in reading ship position.");
        }
        catch (ArrayIndexOutOfBoundsException e) {
            throw new BattleshipBoardException("Ship can't stay out of board.");
        }
    }

    public static void printSetPlayerShipPosition(Player player, Scanner sc) {
        String message = "";

        while (player.getShips().size() < BattleshipGame.getLimitNumberOfShips()) {
            clearScreen();
            printHeader();
            printBattleshipBoard(player.getBoard());

            printMessage("Setting " + player + " Ship Positions");
            printMessage(message);

            try {
                UI.readChooseShipPosition(player, sc);

                message = "";

                clearScreen();
                printHeader();
                printBattleshipBoard(player.getBoard());

                System.out.println("Are you sure about that position?\n");
                System.out.print("Press ENTER to continue or [c] to cancel: ");
                sc.nextLine(); // clean buffer
                String response = sc.nextLine();

                if (response.isEmpty()) {
                    // Do nothing, just continue
                }
                // Undo the choosed ship position
                else  {
                    // Remove ship from game ship list
                    Ship shipToRemove = player.getShips().get(player.getShips().size() - 1);
                    player.getShips().remove(shipToRemove);

                    // Remove ship position from board matrix
                    for (ShipPosition shipPosition : shipToRemove.getShipPositions()) {
                        int row = shipPosition.getPosition().getRow();
                        int column = shipPosition.getPosition().getColumn();
                        player.getBoard().getMatrix()[row][column] = null;
                    }
                }
            }
            catch (BattleshipGameException e) {
                message = "";
                message += e.getMessage();
            }
            catch (BattleshipBoardException e) {
                message = "";
                message += e.getMessage();
            }
        }
    }

    public static void printPlayerTurn(BattleshipGame game, Scanner sc) {
        String message = "";

        boolean error;

        do {
            clearScreen();
            printHeader();
            printBattleshipBoard(game.getOpponent().getBoard());
            printMessage("Turn: " + game.getCurrentPlayer());
            printMessage(message);

            try {
                Position position = readPlayerTargetPosition(sc);
                // if had errors before, then clear the messages
                message = "";

                if (game.checkPlayerPosition(game.getOpponent().getBoard(), position)) {
                    message += "A ship position was hitted. In position " + BattleshipPosition.fromPosition(position) + ".";
                }
                else {
                    message += "Miss!";
                }

                Ship ship = game.checkPlayerBattleships(game.getOpponent());

                if (ship != null) {
                    message += "\n\n" + ship.getShipType() + " was sunk!";
                }

                error = false;
            }
            catch (BattleshipBoardException e) {
                message = "";
                message += e.getMessage();
                error = true;
            }
            catch (BattleshipGameException e) {
                message = "";
                message += e.getMessage();
                error = true;
            }
        }
        while(error);


        // Updating UI with the player target position
        UI.clearScreen();
        UI.printHeader();
        UI.printBattleshipBoard(game.getOpponent().getBoard());
        printMessage(message);

        if (game.getOpponent().getShips().size() > 0) {
            sc.nextLine();
            System.out.print("Press ENTER to change the turn...");
            sc.nextLine();
        }
        else {
            printMessage("END OF BATTLE. " + game.getCurrentPlayer() + " won the match.");
        }
    }

    public static void breakline() {
        System.out.println();
    }

    private static void printRow(int rowIndex, int columns, BattleshipBoard board) {
        // Aligning the row index to view like this:
        //  1 - - - - -
        // 10 - - - - -
        // And not like this:
        // 1 - - - - -
        // 10 - - - - -
        if (!IntegerUtil.isTwoDigit(columns)) {
            System.out.print(rowIndex + SPACE);
        }
        else {
            if (!IntegerUtil.isTwoDigit(rowIndex)) System.out.print(SPACE + rowIndex + SPACE);
            else System.out.print(rowIndex + SPACE);
        }

        printColumns(columns, rowIndex, board);
        System.out.println();
    }

    private static void printColumns(int columns, int row, BattleshipBoard board) {
        ShipPosition[][] matrix = board.getMatrix();
        boolean[][] playerChoosedPosition = board.getPlayerChoosedPosition();

        for (int i = 0; i < columns; i++) {
            if (i == 0) {
                if (playerChoosedPosition[row - 1][i]) {
                    if (matrix[row - 1][i] != null) System.out.print((matrix[row - 1][i].isMarked() ? "#" : "~"));
                    else System.out.print("m");
                }
                else {
                    if (matrix[row - 1][i] != null) System.out.print((matrix[row - 1][i].isMarked() ? "#" : "~"));
                    else System.out.print("~");
                }
            }
            else {
                if (playerChoosedPosition[row - 1][i]) {
                    if (matrix[row - 1][i] != null) System.out.print(SPACE + (matrix[row - 1][i].isMarked() ? "#" : "~"));
                    else System.out.print(" m");
                }
                else {
                    if (matrix[row - 1][i] != null) System.out.print((matrix[row - 1][i].isMarked() ? " #" : " ~"));
                    else System.out.print(" ~");
                }
            }
        }
    }

    // print row: a b c d e f g ...
    private static void printLettersRow(int columns) {
        for (int i = 0; i < columns; i++) {
            if (i == 0) System.out.print('a');
            else System.out.print(SPACE + (char)('a' + i));
        }
        System.out.println();
    }

    private static void printOffset(int offset) {
        for (int i = 0; i < offset; i++) {
            System.out.print(SPACE);
        }
    }

}
