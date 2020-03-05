package engine;

import battleship.ship.ShipType;
import battleship.board.BattleshipBoardException;
import battleship.board.Position;
import battleship.ship.Ship;
import battleship.ship.ShipPosition;
import battleship.board.BattleshipBoard;
import config.GameConfiguration;

import java.util.*;
import java.util.stream.Collectors;

public class BattleshipGame {
    Player player1 = new Player(1, new BattleshipBoard(GameConfiguration.NUMBER_OF_ROWS, GameConfiguration.NUMBER_OF_COLUMNS));
    Player player2 = new Player(2, new BattleshipBoard(GameConfiguration.NUMBER_OF_ROWS, GameConfiguration.NUMBER_OF_COLUMNS));
    Player currentPlayer;

    public BattleshipGame() {
        this.currentPlayer = player1;
    }

    // Check if the position entered by player hitted a ship position
    public boolean checkPlayerPosition(BattleshipBoard board, Position position) {
        if (!board.positionExists(position)) {
            throw new BattleshipGameException("Invalid position!");
        }

        board.getPlayerChoosedPosition()[position.getRow()][position.getColumn()] = true;

        if (board.checkPosition(position) != null) {
            board.getMatrix()[position.getRow()][position.getColumn()].setMarker(true);
            return true;
        }
        return false;
    }

    // Check if player battleships were sunk, so add to sunkShips list
    public Ship checkPlayerBattleships(Player player) {
        Ship markedShip = null; // marked ship which was sunk

        for (Ship ship : player.getShips()) {
            boolean wasSunk = true;

            for (ShipPosition shipPosition : ship.getShipPositions()) {
                if (!shipPosition.isMarked()) {
                    wasSunk = false;
                    break;
                }
            }

            if (wasSunk) {
                // check if the ship already have been sunk
                if (!player.getSunkShips().contains(ship)) {
                    markedShip = ship;
                }
            }
        }

        if (markedShip != null) {
            player.getShips().remove(markedShip);
            player.getSunkShips().add(markedShip);
            return markedShip;
        }
        return null;
    }

    public void hideAllPlayersShips() {
        player1.hideAllShip();
        player2.hideAllShip();
    }

    public boolean isOver() {
        return player1.getShips().size() == 0 | player2.getShips().size() == 0;
    }

    public static int getLimitNumberOfShips() {
        return
                GameConfiguration.NUMBER_OF_CARRIERS
                + GameConfiguration.NUMBER_OF_BATTLESHIPS
                + GameConfiguration.NUMBER_OF_DESTROYERS
                + GameConfiguration.NUMBER_OF_SUBMARINES
                + GameConfiguration.NUMBER_OF_PATROL_BOATS;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Player getOpponent() {
        return getCurrentPlayer() == player1 ? player2 : player1;
    }

    public void nextPlayer() {
        Player nextPlayer = getCurrentPlayer() == player1 ? player2 : player1;
        setCurrentPlayer(nextPlayer);
    }

}
