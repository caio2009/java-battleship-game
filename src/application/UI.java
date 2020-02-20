package application;

import battleship.Ship;
import battleship.ShipPosition;
import battleship.board.BattleshipBoard;
import utils.IntegerUtil;

public class UI {

    public static final String SPACE = " ";

    public static void printHeader() {
        System.out.println("**********   BATTLESHIP   **********");
    }

    public static void printBattleshipBoard(BattleshipBoard board, ShipPosition[][] matrix) {
        // Set offset to align letters row
        if (!IntegerUtil.isTwoDigit(board.getRows())) printOffset(2);
        else printOffset(3);

        printLettersRow(board.getColumns());

        for (int i = 0; i < board.getRows(); i++) {
            printRow(i + 1, board.getColumns(), matrix);
        }
    }

    private static void printRow(int rowIndex, int columns, ShipPosition[][] matrix) {
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

        printColumns(columns, rowIndex, matrix);
        System.out.println();
    }

    private static void printColumns(int columns, int row, ShipPosition[][] matrix) {
        for (int i = 0; i < columns; i++) {
            if (i == 0) {
                if (matrix[row - 1][i] != null) System.out.print((matrix[row - 1][i].isMarked() ? "#" : "-"));
                else System.out.print("-");
            }
            else {
                if (matrix[row - 1][i] != null) System.out.print((matrix[row - 1][i].isMarked() ? " #" : " -"));
                else System.out.print(" -");
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
