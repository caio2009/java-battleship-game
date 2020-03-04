package battleship.board;

import battleship.Ship;
import battleship.ShipPosition;
import battleship.ShipType;
import config.GameConfiguration;

import java.util.ArrayList;
import java.util.List;

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
        if (!positionExists(position)) return null;
        return matrix[position.getRow()][position.getColumn()];
    }

    public void placeShip(Ship ship, int direction, Position refPosition) {
        List<ShipPosition> shipPositions = new ArrayList<>();

        // Horizontal
        if (direction == 1) {
            for (int i = 0; i < shipPositionNumber(ship.getShipType()); i++) {
                Position position = new Position(refPosition.getRow(), refPosition.getColumn() + i);

                if (checkPosition(position) != null) {
                    throw new BattleshipBoardException("Can't set position to this ship. Because it's already occupied.");
                }

                ShipPosition shipPosition = new ShipPosition(position, true);

                shipPositions.add(shipPosition);
                matrix[position.getRow()][position.getColumn()] = shipPosition;
            }
        }
        // Vertical
        else {
            for (int i = 0; i < shipPositionNumber(ship.getShipType()); i++) {
                Position position = new Position(refPosition.getRow() + i, refPosition.getColumn());

                if (checkPosition(position) != null) {
                    throw new BattleshipBoardException("Can't set position to this ship. Because it's already occupied.");
                }

                ShipPosition shipPosition = new ShipPosition(position, true);

                shipPositions.add(shipPosition);
                matrix[position.getRow()][position.getColumn()] = shipPosition;
            }
        }

        ship.setShipPositions(shipPositions);
    }

    public boolean checkPositionValidation(Position refPosition, int direction, ShipType type) {
        boolean validation = true;
        // Horizontal
        if (direction == 1) {
            // Checking the around positions. If there is a ship position then validation is false.
            for (int i = 0; i < shipPositionNumber(type); i++) {
                Position position = new Position(refPosition.getRow(), refPosition.getColumn() + i);
                if (i == 0) {
                    // up
                    if (checkPosition(new Position(position.getRow() - 1, position.getColumn())) != null) {
                        validation = false;
                        break;
                    };
                    // down
                    if (checkPosition(new Position(position.getRow() + 1, position.getColumn())) != null) {
                        validation = false;
                        break;
                    };
                    // left
                    if (checkPosition(new Position(position.getRow(), position.getColumn() - 1)) != null) {
                        validation = false;
                        break;
                    }
                    // northwest
                    if (checkPosition(new Position(position.getRow() - 1, position.getColumn() - 1)) != null) {
                        validation = false;
                        break;
                    }
                    // southwest
                    if (checkPosition(new Position(position.getRow() + 1, position.getColumn() - 1)) != null) {
                        validation = false;
                        break;
                    }
                }
                else if (i == shipPositionNumber(type) - 1) {
                    // up
                    if (checkPosition(new Position(position.getRow() - 1, position.getColumn())) != null) {
                        validation = false;
                        break;
                    };
                    // down
                    if (checkPosition(new Position(position.getRow() + 1, position.getColumn())) != null) {
                        validation = false;
                        break;
                    };
                    // right
                    if (checkPosition(new Position(position.getRow(), position.getColumn() + 1)) != null) {
                        validation = false;
                        break;
                    }
                    // northeast
                    if (checkPosition(new Position(position.getRow() - 1, position.getColumn() + 1)) != null) {
                        validation = false;
                        break;
                    }
                    // southeast
                    if (checkPosition(new Position(position.getRow() + 1, position.getColumn() + 1)) != null) {
                        validation = false;
                        break;
                    }
                }
                else {
                    // up
                    if (checkPosition(new Position(position.getRow() - 1, position.getColumn())) != null) {
                        validation = false;
                        break;
                    };
                    // down
                    if (checkPosition(new Position(position.getRow() + 1, position.getColumn())) != null) {
                        validation = false;
                        break;
                    };
                }
            }
        }
        // Vertical
        else {
            for (int i = 0; i < shipPositionNumber(type); i++) {
                Position position = new Position(refPosition.getRow(), refPosition.getColumn() + i);
                if (i == 0) {
                    // up
                    if (checkPosition(new Position(position.getRow() - 1, position.getColumn())) != null) {
                        validation = false;
                        break;
                    };
                    // right
                    if (checkPosition(new Position(position.getRow(), position.getColumn() + 1)) != null) {
                        validation = false;
                        break;
                    };
                    // left
                    if (checkPosition(new Position(position.getRow(), position.getColumn() - 1)) != null) {
                        validation = false;
                        break;
                    }
                    // northwest
                    if (checkPosition(new Position(position.getRow() - 1, position.getColumn() - 1)) != null) {
                        validation = false;
                        break;
                    }
                    // northeast
                    if (checkPosition(new Position(position.getRow() - 1, position.getColumn() + 1)) != null) {
                        validation = false;
                        break;
                    }
                }
                else if (i == shipPositionNumber(type) - 1) {
                    // down
                    if (checkPosition(new Position(position.getRow() + 1, position.getColumn())) != null) {
                        validation = false;
                        break;
                    };
                    // left
                    if (checkPosition(new Position(position.getRow(), position.getColumn() - 1)) != null) {
                        validation = false;
                        break;
                    };
                    // right
                    if (checkPosition(new Position(position.getRow(), position.getColumn() + 1)) != null) {
                        validation = false;
                        break;
                    }
                    // southwest
                    if (checkPosition(new Position(position.getRow() + 1, position.getColumn() - 1)) != null) {
                        validation = false;
                        break;
                    }
                    // southeast
                    if (checkPosition(new Position(position.getRow() + 1, position.getColumn() + 1)) != null) {
                        validation = false;
                        break;
                    }
                }
                else {
                    // right
                    if (checkPosition(new Position(position.getRow(), position.getColumn() + 1)) != null) {
                        validation = false;
                        break;
                    };
                    // left
                    if (checkPosition(new Position(position.getRow(), position.getColumn() - 1)) != null) {
                        validation = false;
                        break;
                    };
                }
            }
        }
        return validation;
    }

    private int shipPositionNumber(ShipType type) {
        if (type == ShipType.CARRIER) return 5;
        if (type == ShipType.BATTLESHIP) return 4;
        if (type == ShipType.DESTROYER) return 3;
        if (type == ShipType.SUBMARINE) return 3;
        return 2; // PATROLBOAT
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

    public boolean[][] getPlayerChoosedPosition() {
        return playerChoosedPosition;
    }

}
