package battleship.board;

import battleship.Position;
import config.GameConfiguration;

public class BattleshipBoard {

    int rows;
    int columns;

    public BattleshipBoard(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
    }

    public boolean positionExists(Position position) {
        int rows = GameConfiguration.NUMBER_OF_ROWS;
        int columns = GameConfiguration.NUMBER_OF_COLUMNS;
        return position.getRow() >= 0 && position.getRow() < rows && position.getColumn() >= 0 && position.getColumn() < columns;
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

}
