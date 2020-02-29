package battleship;

import engine.BattleshipGame;

import java.util.List;

public class Ship {

    private List<ShipPosition> positions;
    private ShipType type;

    public Ship(ShipType type, List<ShipPosition> positions) {
        this.positions = positions;
        this.type = type;
    }

    public List<ShipPosition> getShipPositions() {
        return positions;
    }

    public void setShipPositions(List<ShipPosition> positions) {
        this.positions = positions;
    }

}
