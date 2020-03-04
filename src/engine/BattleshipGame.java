package engine;

import battleship.ShipType;
import battleship.board.Position;
import battleship.Ship;
import battleship.ShipPosition;
import battleship.board.BattleshipBoard;
import config.GameConfiguration;

import java.util.*;
import java.util.stream.Collectors;

public class BattleshipGame {

    BattleshipBoard board;
    List<Ship> ships = new ArrayList<>();
    List<Ship> sunkShips = new ArrayList<>();

    public BattleshipGame(BattleshipBoard board) {
        this.board = board;
        /*initShips();
        initShipPositions();*/
    }

    public boolean checkPlayerPosition(Position position) {
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

    // Check if battleships were sunk, so add to sunkShips list
    public Ship checkBattleships() {
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
            return markedShip;
        }
        return null;
    }

    public void addShip(ShipType type, int direction, Position position) {
        // direction: 1-Horizontal   2-Vertical
        Ship ship = new Ship(type);
        ships.add(ship);
        board.placeShip(ship, direction, position);
    }

    public int getNumberOfShipsByType(ShipType type) {
        List<Ship> filterdShips = ships.stream().filter(ship -> ship.getShipType() == type).collect(Collectors.toList());
        return filterdShips.size();
    }

    // Get ship type that is missing in the game order by ships position quantity
    public ShipType getMissingShipType() {
        if (getNumberOfShipsByType(ShipType.CARRIER) < GameConfiguration.NUMBER_OF_CARRIERS) return ShipType.CARRIER;
        if (getNumberOfShipsByType(ShipType.BATTLESHIP) < GameConfiguration.NUMBER_OF_BATTLESHIPS) return ShipType.BATTLESHIP;
        if (getNumberOfShipsByType(ShipType.DESTROYER) < GameConfiguration.NUMBER_OF_DESTROYERS) return ShipType.DESTROYER;
        if (getNumberOfShipsByType(ShipType.SUBMARINE) < GameConfiguration.NUMBER_OF_SUBMARINES) return ShipType.SUBMARINE;
        return ShipType.PATROLBOAT;
    }

    public int getLimitNumberOfShips() {
        return
                GameConfiguration.NUMBER_OF_CARRIERS
                + GameConfiguration.NUMBER_OF_BATTLESHIPS
                + GameConfiguration.NUMBER_OF_DESTROYERS
                + GameConfiguration.NUMBER_OF_SUBMARINES
                + GameConfiguration.NUMBER_OF_PATROL_BOATS;
    }

    // Mark all ship positions marker of ship list with false
    public void hideAllShip() {
        ships.forEach(x -> {
            x.getShipPositions().forEach(shipPosition -> {
                shipPosition.setMarker(false);
            });
        });
    }

    public BattleshipBoard getBoard() {
        return board;
    }

    public List<Ship> getShips() {
        return ships;
    }

    public List<Ship> getSunkShips() {
        return sunkShips;
    }

    /*private void initShips() {
        // Instance for tests
        Ship ship1 = new Ship(
                ShipType.SUBMARINE,
                Arrays.asList(
                        new ShipPosition(new BattleshipPosition('c', 3).toPosition(), true),
                        new ShipPosition(new BattleshipPosition('c', 4).toPosition(), true),
                        new ShipPosition(new BattleshipPosition('c', 5).toPosition(), true)
                )
        );
        Ship ship2 = new Ship(
                ShipType.DESTROYER,
                Arrays.asList(
                        new ShipPosition(new BattleshipPosition('a', 1).toPosition(), true),
                        new ShipPosition(new BattleshipPosition('b', 1).toPosition(), true),
                        new ShipPosition(new BattleshipPosition('c', 1).toPosition(), true)
                )
        );
        ships.add(ship1);
        ships.add(ship2);
    }

    private void initShipPositions() {
        ships.forEach(ship -> {
            ship.getShipPositions().forEach(shipPosition -> {
                int row = shipPosition.getPosition().getRow();
                int column = shipPosition.getPosition().getColumn();
                board.getMatrix()[row][column] = shipPosition;
            });
        });
    }*/

}
