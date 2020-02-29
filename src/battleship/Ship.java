package battleship;

import engine.BattleshipGame;

import java.util.List;

public class Ship {

    private List<ShipPosition> positions;
    private ShipType type;

    public Ship(ShipType type, List<ShipPosition> positions) {
        if (type == ShipType.CARRIER) {
            if (positions.size() != 5) {
                throw new ShipException(type + " must contain 5 positions.");
            }
        }

        if (type == ShipType.BATTLESHIP) {
            if (positions.size() != 4) {
                throw new ShipException(type + " must contain 4 positions.");
            }
        }

        if (type == ShipType.DESTROYER) {
            if (positions.size() != 3) {
                throw new ShipException(type + " must contain 3 positions.");
            }
        }

        if (type == ShipType.SUBMARINE) {
            if (positions.size() != 3) {
                throw new ShipException(type + " must contain 3 positions.");
            }
        }

        if (type == ShipType.PATROLBOAT) {
            if (positions.size() != 2) {
                throw new ShipException(type + " must contain 2 positions.");
            }
        }

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
