package battleship;

public class ShipPosition {

    private Position position;
    private boolean marker;

    public ShipPosition(Position position) {
        this.position = position;
        marker = false;
    }

    public ShipPosition(Position position, boolean marker) {
        this.position = position;
        this.marker = marker;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public boolean isMarked() {
        return marker;
    }

    public void setMarker(boolean marker) {
        this.marker = marker;
    }

}
