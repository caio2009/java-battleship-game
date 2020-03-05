package engine;

import battleship.board.BattleshipBoard;
import battleship.ship.Ship;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

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
        return "Player #" + id;
    }

}
