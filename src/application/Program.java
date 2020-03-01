package application;

import battleship.Position;
import battleship.Ship;
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
        String message = "";

        while(engine.getShips().size() > 0) {
            UI.clearScreen();
            UI.printHeader();
            UI.printBattleshipBoard(board);
            UI.printMessage(message);

            try {
                Position position = UI.readPlayerTargetPosition(sc);

                message = ""; // clearing message

                if (engine.checkPlayerPosition(position)) {
                    message += "A ship position was hitted. In position " + BattleshipPosition.fromPosition(position) + ".";
                }
                else {
                    message += "Miss!";
                }

                Ship ship = engine.checkBattleships();

                if (ship != null) {
                    message += "\n\n";
                    message += ship.getShipType() + " was sunk!";
                }
            }
            catch (BattleshipBoardException e) {
                message = "";
                message += e.getMessage();
            }
            catch (BatteshipGameException e) {
                message = "";
                message += e.getMessage();
            }
        }

        UI.clearScreen();
        UI.printHeader();
        UI.printBattleshipBoard(board);
        UI.printMessage(message);

        sc.close();
    }

}
