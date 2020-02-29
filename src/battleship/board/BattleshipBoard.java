package battleship.board;

import battleship.Position;
import battleship.ShipPosition;
import config.GameConfiguration;

public class BattleshipBoard {

    int rows;
    int columns;
    ShipPosition[][] matrix = new ShipPosition[GameConfiguration.NUMBER_OF_ROWS][GameConfiguration.NUMBER_OF_COLUMNS];
    boolean[][] playerChoosedPosition = new boolean[GameConfiguration.NUMBER_OF_ROWS][GameConfiguration.NUMBER_OF_COLUMNS];

    public BattleshipBoard(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
    }

    public boolean positionExists(Position position) {
        int rows = GameConfiguration.NUMBER_OF_ROWS;
        int columns = GameConfiguration.NUMBER_OF_COLUMNS;
        return position.getRow() >= 0 && position.getRow() < rows && position.getColumn() >= 0 && position.getColumn() < columns;
    }

    public ShipPosition checkPosition(Position position) {
        return matrix[position.getRow()][position.getColumn()];
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public ShipPosition[][] getMatrix() {
        return matrix;
    }

    public boolean[][] getPlayerChoosedPosition() { return playerChoosedPosition; }

}
