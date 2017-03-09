package tomtomsen.tictactoe.core.exception;

import tomtomsen.tictactoe.core.Piece;

/**
 * Exception
 */
public final class FieldBlockedException extends Exception {
  /**
   * Piece which was blocking the field
   */
  final private Piece blockingPiece;

  /**
   * Constructor
   *
   * @param blockingPiece blocking piece
   */
  public FieldBlockedException(final Piece blockingPiece) {
    super();

    this.blockingPiece = blockingPiece;
  }

  /**
   * Returns the blocking piece
   */
  public Piece getBlockingPiece() {
    return blockingPiece;
  }
}
