package application;

import battleship.Position;
import battleship.Ship;
import battleship.ShipPosition;
import battleship.board.BattleshipBoard;
import battleship.board.BattleshipPosition;
import config.GameConfiguration;
import engine.BattleshipGame;

import java.util.Arrays;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        BattleshipBoard board = new BattleshipBoard(GameConfiguration.NUMBER_OF_ROWS, GameConfiguration.NUMBER_OF_COLUMNS);
        BattleshipGame engine = new BattleshipGame();

        UI.printHeader();
        UI.printBattleshipBoard(board, engine.getMatrix());

        Position position = UI.readPlayerInput(sc);
        System.out.println(position);

        sc.close();
    }

}
