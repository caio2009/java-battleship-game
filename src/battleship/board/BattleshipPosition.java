package battleship.board;

import battleship.Position;
import config.GameConfiguration;

public class BattleshipPosition {

    int row;
    char column;

    public BattleshipPosition(char column, int row) {
        if (
                column < 'a'|| column > (char)('a' + GameConfiguration.NUMBER_OF_COLUMNS - 1)
                || row < 0 || row > GameConfiguration.NUMBER_OF_ROWS
        ) {
            throw new BattleshipBoardException("Position must be between a1 to " + (char)('a' + GameConfiguration.NUMBER_OF_COLUMNS - 1) + GameConfiguration.NUMBER_OF_ROWS);
        }
        this.column = column;
        this.row = row;
    }

    public Position toPosition() {
        return new Position(row - 1, (int) column - 'a');
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public char getColumn() {
        return column;
    }

    public void setColumn(char column) {
        this.column = column;
    }

}
