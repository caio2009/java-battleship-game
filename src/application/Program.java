package application;

import battleship.Position;
import battleship.board.BattleshipBoard;
import battleship.board.BattleshipBoardException;
import battleship.board.BattleshipPosition;
import config.GameConfiguration;
import engine.BattleshipGame;
import engine.BatteshipGameException;

import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        BattleshipBoard board = new BattleshipBoard(GameConfiguration.NUMBER_OF_ROWS, GameConfiguration.NUMBER_OF_COLUMNS);
        BattleshipGame engine = new BattleshipGame(board);

        UI.printHeader();
        UI.printBattleshipBoard(board);

        try {
            System.out.println();
            Position position = UI.readPlayerTargetPosition(sc);
            // System.out.println(position);

            System.out.println();
            if (engine.checkPlayerPosition(position)) {
                System.out.println("A ship position was hitted. In position " + BattleshipPosition.fromPosition(position) + ".");
            }
            else {
                System.out.println("Miss!");
            }
        }
        catch (BattleshipBoardException e) {
            System.out.println();
            System.out.println(e.getMessage());
        }
        catch (BatteshipGameException e) {
            System.out.println();
            System.out.println(e.getMessage());
        }

        sc.close();
    }

}
