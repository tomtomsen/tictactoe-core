package tomtomsen.tictactoe.core;

/**
 * A tic tac toe player
 */
public interface Player {
  /**
   * Asks the player to make a move
   *
   * @param  board     board
   * @param  yourPiece Piece
   *
   * @return           position on baord
   */
  int[] makeMove(Board board, Piece yourPiece);
  /**
   * Give up
   *
   * @return true if game should end, otherwise false
   */
  boolean giveUp();
  //void assignPiece(Piece piece);
}
