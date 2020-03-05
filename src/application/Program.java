package application;

import battleship.board.Position;
import battleship.ship.Ship;
import battleship.ship.ShipPosition;
import battleship.board.BattleshipBoard;
import battleship.board.BattleshipBoardException;
import battleship.board.BattleshipPosition;
import config.GameConfiguration;
import engine.BattleshipGameException;
import engine.BattleshipGame;

import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        BattleshipGame game = new BattleshipGame();
        String message = "";

        // Setting the players ship position
        UI.printSetPlayerShipPosition(game.getCurrentPlayer(), sc);
        game.nextPlayer();
        UI.printSetPlayerShipPosition(game.getCurrentPlayer(), sc);

        game.hideAllPlayersShips();
        game.nextPlayer();

        // Starting the battle
        while (!game.isOver()) {
            UI.printPlayerTurn(game, sc);
            game.nextPlayer();
        }

        /*while(game.getShips().size() > 0) {
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
            catch (BattleshipGameException e) {
                message = "";
                message += e.getMessage();
            }
        }*/

        sc.close();
    }

}
