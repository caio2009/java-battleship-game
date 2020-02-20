package application;

import battleship.Ship;
import battleship.ShipPosition;
import battleship.board.BattleshipBoard;
import battleship.board.BattleshipPosition;
import config.GameConfiguration;
import engine.BattleshipGame;

import java.util.Arrays;

public class Program {

    public static void main(String[] args) {
        BattleshipBoard board = new BattleshipBoard(GameConfiguration.NUMBER_OF_ROWS, GameConfiguration.NUMBER_OF_COLUMNS);
        BattleshipGame engine = new BattleshipGame();

        UI.printHeader();
        UI.printBattleshipBoard(board, engine.getMatrix());
    }

}
