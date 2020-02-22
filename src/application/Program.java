package application;

import battleship.Position;
import battleship.board.BattleshipBoard;
import battleship.board.BattleshipBoardException;
import config.GameConfiguration;
import engine.BattleshipGame;

import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        BattleshipBoard board = new BattleshipBoard(GameConfiguration.NUMBER_OF_ROWS, GameConfiguration.NUMBER_OF_COLUMNS);
        BattleshipGame engine = new BattleshipGame(board);

        UI.printHeader();
        UI.printBattleshipBoard(board);

        try {
            Position position = UI.readPlayerTargetPosition(sc);
            System.out.println(position);
        }
        catch (BattleshipBoardException e) {
            System.out.println(e.getMessage());
        }

        sc.close();
    }

}
