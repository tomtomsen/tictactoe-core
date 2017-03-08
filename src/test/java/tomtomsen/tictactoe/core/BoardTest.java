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

    @Test(expected = Exception.class)
    public void CantPlaceAPieceWhereAPieceIs() throws Exception {
        Board board = new Board();
        board.placePiece(Piece.CIRCLE, 0, 0);
        board.placePiece(Piece.CROSS, 0, 0);
    }

    @Test(expected = Exception.class)
    public void CantPlacePieceTooFarLeft() throws Exception {
        Board board = new Board();

        board.placePiece(Piece.CIRCLE, -1, 0);
    }

    @Test(expected = Exception.class)
    public void CantPlacePieceTooFarRight() throws Exception {
        Board board = new Board();

        board.placePiece(Piece.CIRCLE, Board.BOARDLENGTH + 1, 0);
    }

    @Test(expected = Exception.class)
    public void CantPlacePieceTooFarUp() throws Exception {
        Board board = new Board();

        board.placePiece(Piece.CIRCLE, 0, -1);
    }

    @Test(expected = Exception.class)
    public void CantPlacePieceTooFarDown() throws Exception {
        Board board = new Board();

        board.placePiece(Piece.CIRCLE, 0, Board.BOARDLENGTH + 1);
    }

    @Test
    public void FullBoard() throws Exception {
        Board board = new Board();
        for(int row = 0; row < board.BOARDLENGTH; row ++) {
            for(int col = 0; col < board.BOARDLENGTH; col ++) {
                board.placePiece(Piece.CIRCLE, col, row);
                assertTrue(board.hasPieceAt(col, row));
                assertEquals(Piece.CIRCLE, board.pieceAt(col, row));
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
