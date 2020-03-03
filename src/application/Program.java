package application;

import battleship.Position;
import battleship.Ship;
import battleship.ShipPosition;
import battleship.board.BattleshipBoard;
import battleship.board.BattleshipBoardException;
import battleship.board.BattleshipPosition;
import config.GameConfiguration;
import engine.BatteshipGameException;
import engine.BattleshipGame;

import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        BattleshipBoard board = new BattleshipBoard(GameConfiguration.NUMBER_OF_ROWS, GameConfiguration.NUMBER_OF_COLUMNS);
        BattleshipGame game = new BattleshipGame(board);
        String message = "";

        while(game.getShips().size() < 5) {
            UI.clearScreen();
            UI.printHeader();
            UI.printBattleshipBoard(board);

            UI.readChooseShipPosition(sc, game);

            UI.clearScreen();
            UI.printHeader();
            UI.printBattleshipBoard(board);

            System.out.println("Are you sure about that position?\n");
            System.out.print("Press [y] to continue or [n] to cancel: ");
            String response = sc.next();

            if (response.equals("y")) {
                // Do nothing, just continue
            }
            // Undo the choosed ship position
            else  {
                // Remove ship from game ship list
                Ship shipToRemove = game.getShips().get(game.getShips().size() - 1);
                game.getShips().remove(shipToRemove);

                // Remove ship position from board matrix
                for (ShipPosition shipPosition : shipToRemove.getShipPositions()) {
                    int row = shipPosition.getPosition().getRow();
                    int column = shipPosition.getPosition().getColumn();
                    game.getBoard().getMatrix()[row][column] = null;
                }
            }
        }

        while(game.getShips().size() > 0) {
            UI.clearScreen();
            UI.printHeader();
            UI.printBattleshipBoard(board);
            UI.printMessage(message);

            try {
                Position position = UI.readPlayerTargetPosition(sc);

                message = ""; // clearing message

                if (game.checkPlayerPosition(position)) {
                    message += "A ship position was hitted. In position " + BattleshipPosition.fromPosition(position) + ".";
                }
                else {
                    message += "Miss!";
                }

                Ship ship = game.checkBattleships();

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
