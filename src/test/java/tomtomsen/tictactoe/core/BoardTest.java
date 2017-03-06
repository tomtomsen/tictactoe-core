package tomtomsen.tictactoe.core;

import org.junit.Test;

import static org.junit.Assert.*;

public class BoardTest {
    @Test
    public void BoardInitializesEmpty() throws Exception {
        Board board = new Board();
        assertTrue(board.isEmpty());
        assertFalse(board.isFull());
    }

    @Test
    public void PlacingAPieceMakesBoardNotEmpty() throws Exception {
        Board board = new Board();
        board.placePiece(Piece.CIRCLE, 0, 0);
        assertFalse(board.isEmpty());
        assertFalse(board.isFull());
    }

    @Test
    public void FullBoard() throws Exception {
        Board board = new Board();
        for(int row = 0; row < board.BOARDSIZE; row ++) {
            for(int col = 0; col < board.BOARDSIZE; col ++) {
                board.placePiece(Piece.CIRCLE, col, row);
            }
        }
        assertFalse(board.isEmpty());
        assertTrue(board.isFull());
    }

    @Test
    public void PlaceAndRetrievePiece() throws Exception {
        Board board = new Board();

        assertEquals(Piece.NONE, board.pieceAt(0, 0));
        board.placePiece(Piece.CROSS, 0, 0);

        assertEquals(Piece.CROSS, board.pieceAt(0, 0));
    }
}
