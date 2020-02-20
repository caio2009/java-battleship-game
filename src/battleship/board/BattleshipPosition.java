package battleship.board;

import battleship.Position;

public class BattleshipPosition {

    int row;
    char column;

    public BattleshipPosition(char column, int row) {
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
