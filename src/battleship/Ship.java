package battleship;

import java.util.List;

public class Ship {

    private List<ShipPosition> positions;

    public Ship() { }

    public Ship(List<ShipPosition> positions) {
        this.positions = positions;
    }

    public List<ShipPosition> getShipPositions() {
        return positions;
    }

    public void setShipPositions(List<ShipPosition> positions) {
        this.positions = positions;
    }

}
