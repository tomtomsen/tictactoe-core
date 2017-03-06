package tomtomsen.tictactoe.core;

public interface Player {
    int[] makeMove(Board board, Piece yourPiece);
    boolean giveUp();
}
