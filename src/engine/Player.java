package engine;

import battleship.board.BattleshipBoard;
import battleship.board.BattleshipBoardException;
import battleship.board.Position;
import battleship.ship.Ship;
import battleship.ship.ShipType;
import config.GameConfiguration;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Player {

    private int id;

    private BattleshipBoard board;
    private List<Ship> ships = new ArrayList<>();
    private List<Ship> sunkShips = new ArrayList<>();

    public Player() {
    }

    public Player(int id, BattleshipBoard board) {
        this.id = id;
        this.board = board;
    }

    public void newShip(ShipType type, int direction, Position position) {
        // direction: 1-Horizontal   2-Vertical
        if(!board.checkPositionValidation(position, direction, type)) {
            throw new BattleshipBoardException("The ship can't touch other ship.");
        }

        Ship ship = new Ship(type);
        board.placeShip(ship, direction, position);
        ships.add(ship);
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

    // Mark all ship positions marker of ship list with false
    public void hideAllShip() {
        ships.forEach(x -> {
            x.getShipPositions().forEach(shipPosition -> {
                shipPosition.setMarker(false);
            });
        });
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BattleshipBoard getBoard() {
        return board;
    }

    public void setBoard(BattleshipBoard board) {
        this.board = board;
    }

    public List<Ship> getShips() {
        return ships;
    }

    public List<Ship> getSunkShips() {
        return sunkShips;
    }

    @Override
    public String toString() {
        return "PLAYER #" + id;
    }

}
