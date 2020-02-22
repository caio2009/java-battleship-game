package engine;

import battleship.Ship;
import battleship.ShipPosition;
import battleship.board.BattleshipBoard;
import battleship.board.BattleshipPosition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BattleshipGame {

    BattleshipBoard board;
    List<Ship> ships = new ArrayList<>();

    public BattleshipGame(BattleshipBoard board) {
        this.board = board;
        initShips();
        initShipPositions();
    }

    public List<Ship> getShips() {
        return ships;
    }

    private void initShips() {
        // Instance for tests
        Ship ship = new Ship(
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
