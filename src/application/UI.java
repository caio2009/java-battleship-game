package application;

import battleship.Position;
import battleship.Ship;
import battleship.ShipPosition;
import battleship.ShipType;
import battleship.board.BattleshipBoard;
import battleship.board.BattleshipBoardException;
import battleship.board.BattleshipPosition;
import engine.BatteshipGameException;
import engine.BattleshipGame;
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
        System.out.println();
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
            throw new BatteshipGameException("Invalid position.");
        }
        catch (NumberFormatException e) {
            throw new BatteshipGameException("Problem in reading target position.");
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

    public static void readChooseShipPosition(Scanner sc, BattleshipGame game) {
        try {
            System.out.println("Choose Ship Direction:\n");
            System.out.println("1-Horizontal   2-Vertical\n");
            System.out.print("Entry: ");

            int direction = sc.nextInt();
            System.out.println();

            System.out.print("Enter the ship position: ");
            String strPosition = sc.next();

            char column = strPosition.substring(0, 1).charAt(0);
            int row = Integer.parseInt(strPosition.substring(1));

            Position position = new BattleshipPosition(column, row).toPosition();

            Ship newShip = new Ship(ShipType.CARRIER);
            game.getBoard().placeShip(newShip, direction, position);
            game.getShips().add(newShip);

            sc.nextLine();
            System.out.print("\nType ENTER to continue...");
            sc.nextLine();
        }
        catch (InputMismatchException e) {
            throw new BatteshipGameException("Invalid entry.");
        }
        catch (NumberFormatException e) {
            throw new BatteshipGameException("Problem in reading ship position.");
        }
        catch (ArrayIndexOutOfBoundsException e) {
            throw new BattleshipBoardException("Ship can't stay out of board.");
        }
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
                    if (matrix[row - 1][i] != null) System.out.print((matrix[row - 1][i].isMarked() ? "#" : "-"));
                    else System.out.print("m");
                }
                else {
                    if (matrix[row - 1][i] != null) System.out.print((matrix[row - 1][i].isMarked() ? "#" : "-"));
                    else System.out.print("-");
                }
            }
            else {
                if (playerChoosedPosition[row - 1][i]) {
                    if (matrix[row - 1][i] != null) System.out.print(SPACE + (matrix[row - 1][i].isMarked() ? "#" : "-"));
                    else System.out.print(" m");
                }
                else {
                    if (matrix[row - 1][i] != null) System.out.print((matrix[row - 1][i].isMarked() ? " #" : " -"));
                    else System.out.print(" -");
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
