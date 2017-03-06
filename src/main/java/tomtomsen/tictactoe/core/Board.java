package tomtomsen.tictactoe.core;

/**
 * Created by olmolmsen on 28.02.2017.
 */

public class Board {

    private Piece[][] board;
    public static final int BOARDSIZE = 3;

    public Board() {
        board = new Piece[BOARDSIZE][BOARDSIZE];
        for(int row = 0; row < BOARDSIZE; row ++) {
            for(int col = 0; col < BOARDSIZE; col ++) {
                board[col][row] = Piece.NONE;
            }
        }
    }

    public void placePiece(Piece piece, int col, int row) throws Exception {
        if (Piece.NONE != pieceAt(col, row)) {
            throw new Exception("already placed " + pieceAt(col, row));
        }

        board[col][row] = piece;
    }

    public boolean isEmpty() throws Exception {

        for(int row = 0; row < BOARDSIZE; row ++) {
            for(int col = 0; col < BOARDSIZE; col ++) {
                if (Piece.NONE != pieceAt(col, row)) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean isFull() throws Exception {
        for(int row = 0; row < BOARDSIZE; row ++) {
            for(int col = 0; col < BOARDSIZE; col ++) {
                if (Piece.NONE == pieceAt(col, row)) {
                    return false;
                }
            }
        }

        return true;
    }

    public Piece pieceAt(int col, int row) throws Exception {
        if (isOutOfBounds(col, row)) {
            throw new Exception("out of bounds");
        }

        return board[col][row];
    }

    public boolean hasPieceAt(int col, int row) throws Exception {
        return Piece.NONE != pieceAt(col, row);
    }

    public boolean isOutOfBounds(int col, int row) {
        return (col < 0 || col >= BOARDSIZE || row < 0 || row >= BOARDSIZE);
    }
}
