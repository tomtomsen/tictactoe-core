package tomtomsen.tictactoe.core;

public class Board {

    private Piece[] board;
    public static final int BOARDLENGTH = 3;
    public static final int BOARDSIZE = BOARDLENGTH*BOARDLENGTH;

    public Board() {
        board = new Piece[BOARDSIZE];
        for(int i = 0; i < BOARDSIZE; i ++) {
            board[i] = Piece.NONE;
        }
    }

    public void placePiece(Piece piece, int col, int row) throws Exception {
        if (Piece.NONE != pieceAt(col, row)) {
            throw new Exception("already placed " + pieceAt(col, row));
        }

        board[calcPos(col, row)] = piece;
    }

    public boolean isEmpty() throws Exception {
        for(int i = 0; i < BOARDSIZE; i ++) {
            if (hasPieceAt(calcCol(i), calcRow(i))) {
                return false;
            }
        }

        return true;
    }

    public boolean isFull() throws Exception {
        for(int i = 0; i < BOARDSIZE; i ++) {
            if (!hasPieceAt(calcCol(i), calcRow(i))) {
                return false;
            }
        }

        return true;
    }

    public Piece pieceAt(int col, int row) throws Exception {
        if (isOutOfBounds(col, row)) {
            throw new Exception("out of bounds");
        }

        return board[calcPos(col, row)];
    }

    public boolean hasPieceAt(int col, int row) throws Exception {
        return Piece.NONE != pieceAt(col, row);
    }

    public boolean isOutOfBounds(int col, int row) {
        return (col < 0 || col >= BOARDLENGTH || row < 0 || row >= BOARDLENGTH);
    }

    private int calcCol(int i) {
        return i % BOARDLENGTH;
    }

    private int calcRow(int i) {
        return (int) i / BOARDLENGTH;
    }

    private int calcPos(int col, int row) {
         return col + row * BOARDLENGTH;
    }
}
