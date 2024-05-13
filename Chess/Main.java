package Chess;

/*

chess -> chess board
pieces
color
player -> 2
move
 */

import java.util.List;

enum Color {
    WHITE,
    BLACK
}
class Player {
    int playerId;
    Color color;
}
class CellPosition {
    char xAxis;
    int yAxis;
}


abstract class Piece {
    Color color;
    abstract boolean validateMove(CellPosition start, CellPosition end);
}

class Rook extends Piece {

    @Override
    boolean validateMove(CellPosition start, CellPosition end) {
        return false;
    }
}
class Knight extends Piece {

    @Override
    boolean validateMove(CellPosition start, CellPosition end) {
        return false;
    }
}
class Bishop extends Piece {

    @Override
    boolean validateMove(CellPosition start, CellPosition end) {
        return false;
    }
}
class King extends Piece {

    @Override
    boolean validateMove(CellPosition start, CellPosition end) {
        return false;
    }
}
class Queen extends Piece {

    @Override
    boolean validateMove(CellPosition start, CellPosition end) {
        return false;
    }
}
class Pawn extends Piece {

    @Override
    boolean validateMove(CellPosition start, CellPosition end) {
        return false;
    }
}

class Cell {
    CellPosition cellPosition;
    Piece pieceOnCell;
}

class ChessBoard {
    List<List<Cell>> cell;
    
    Cell getCell(CellPosition cellPosition) {
        return null;
    }
    
    void movePiece(CellPosition start, CellPosition end) {
        // first validate the move by getting the start cell
        Cell startCell=  getCell(start);
        Piece pieceOnStartCell = startCell.pieceOnCell;
        if(pieceOnStartCell.validateMove(start, end)) {
            // move the cell.
        } 
    }
}

class Move {
    CellPosition start;
    CellPosition end;
    Piece movingPiece;
    Piece capturedPiece;
    Player player;
}

class ChessGame {
    ChessBoard chessBoard;
    List<Player> players;
    List<Move> moveHistory;
    Player currentPlayer;
    
    void initializeGame() {
        // instantiates the containing objects.
    }
    
    void makeMove(CellPosition start, CellPosition end) {
        chessBoard.movePiece(start, end);
        // then, switch players.
    }
    
}
