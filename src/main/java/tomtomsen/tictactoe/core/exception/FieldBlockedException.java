package tomtomsen.tictactoe.core.exception;

import tomtomsen.tictactoe.core.Piece;

/**
 * Exception
 */
public final class FieldBlockedException extends Exception {
  private Piece blockingPiece;

  /**
   * Constructor
   *
   * @param blockingPiece blocking piece
   */
  public FieldBlockedException(Piece blockingPiece) {
    this.blockingPiece = blockingPiece;
  }

  /**
   * Returns the blocking piece
   */
  public Piece getPiece() {
    return blockingPiece;
  }
}
