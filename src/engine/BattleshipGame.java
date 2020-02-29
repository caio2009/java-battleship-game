package engine;

import battleship.Position;
import battleship.Ship;
import battleship.ShipPosition;
import battleship.ShipType;
import battleship.board.BattleshipBoard;
import battleship.board.BattleshipPosition;

import java.util.*;

public class BattleshipGame {

    BattleshipBoard board;
    List<Ship> ships = new ArrayList<>();
    List<Ship> sunkShips = new ArrayList<>();

    public BattleshipGame(BattleshipBoard board) {
        this.board = board;
        initShips();
        initShipPositions();
    }

    public boolean checkPlayerPosition(Position position) {
        if (!board.positionExists(position)) {
            throw new BatteshipGameException("Invalid position!");
        }

        board.getPlayerChoosedPosition()[position.getRow()][position.getColumn()] = true;

        if (board.checkPosition(position) != null) {
            board.getMatrix()[position.getRow()][position.getColumn()].setMarker(true);
            return true;
        }
        return false;
    }

    // Check if battleships were sunk, so add to sunkShips list
    public void checkBattleships() {
        Ship markedShip = null; // marked ship which was sunk

        for (Ship ship : ships) {
            boolean wasSunk = true;

            for (ShipPosition shipPosition : ship.getShipPositions()) {
                if (!shipPosition.isMarked()) {
                    wasSunk = false;
                    break;
                }
            }

            if (wasSunk) {
                // check if the ship already have been sunk
                if (!sunkShips.contains(ship)) {
                    markedShip = ship;
                }
            }
        }

        if (markedShip != null) {
            ships.remove(markedShip);
            sunkShips.add(markedShip);
        }
    }

    public List<Ship> getShips() {
        return ships;
    }

    public List<Ship> getSunkShips() {
        return sunkShips;
    }

    private void initShips() {
        // Instance for tests
        Ship ship = new Ship(
                ShipType.SUBMARINE,
                Arrays.asList(
                        new ShipPosition(new BattleshipPosition('a', 1).toPosition()/*, true*/),
                        new ShipPosition(new BattleshipPosition('b', 1).toPosition()/*, true*/),
                        new ShipPosition(new BattleshipPosition('c', 1).toPosition()/*, true*/)
                )
        );
        ships.add(ship);
    }

    private void initShipPositions() {
        ships.forEach(ship -> {
            ship.getShipPositions().forEach(shipPosition -> {
                int row = shipPosition.getPosition().getRow();
                int column = shipPosition.getPosition().getColumn();
                board.getMatrix()[row][column] = shipPosition;
            });
        });
    }

}
