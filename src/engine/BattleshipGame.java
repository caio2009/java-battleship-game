package engine;

import battleship.Ship;
import battleship.ShipPosition;
import battleship.board.BattleshipPosition;
import config.GameConfiguration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BattleshipGame {

    List<Ship> ships = new ArrayList<>();
    ShipPosition[][] matrix = new ShipPosition[GameConfiguration.NUMBER_OF_ROWS][GameConfiguration.NUMBER_OF_COLUMNS];

    public BattleshipGame() {
        initShips();
        initMatrix();
    }

    public List<Ship> getShips() {
        return ships;
    }

    public ShipPosition[][] getMatrix() {
        return matrix;
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

    private void initMatrix() {
        ships.forEach(ship -> {
            ship.getShipPositions().forEach(shipPosition -> {
                int row = shipPosition.getPosition().getRow();
                int column = shipPosition.getPosition().getColumn();
                matrix[row][column] = shipPosition;
            });
        });
    }

}
