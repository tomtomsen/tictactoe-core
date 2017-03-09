package tomtomsen.tictactoe.core;

import tomtomsen.tictactoe.core.exception.OutOfBoundsException;
import tomtomsen.tictactoe.core.exception.FieldBlockedException;

/**
 * Represents a tic tac toe board
 *
 * A board contains three columns and three rows.
 * There are nine fields which can be filled with cross or circle pieces.
 */
public class Board {

  /**
   * a list of all fields
   */
  private Piece[] fields;
  /**
   * A board is three by three squares long
   */
  public static final int BOARDLENGTH = 3;
  /**
   * Total field count
   */
  public static final int FIELDCOUNT = BOARDLENGTH * BOARDLENGTH;

  /**
   * Creates a board intance
   */
  public Board() {
    fields = new Piece[FIELDCOUNT];
  }

  /**
   * places a piece on a specific position
   *
   * @param  piece   Piece to be placed
   * @param  col     Column
   * @param  row     Row
   * @throws OutOfBoundsException  If given position is not on the board
   * @throws FieldBlockedException If given position is blocked by a piece
   */
  public void placePiece(final Piece piece, final Location location)
    throws OutOfBoundsException, FieldBlockedException {

    if (null != pieceAt(location)) {
      throw new FieldBlockedException(pieceAt(location));
    }

    fields[calcPos(location)] = piece;
  }

  /**
   * Checks if the current board is empty
   *
   * @return true if board is empty, otherwise false
   */
  public boolean isEmpty() throws OutOfBoundsException {
    boolean pieceFound = false;

    for (int i = 0; i < FIELDCOUNT && !pieceFound; i ++) {
      if (hasPieceAt(calcLocation(i))) {
        pieceFound = true;
      }
    }

    return !pieceFound;
  }

  /**
   * Checks if the current board is full
   *
   * @return true if there is no empty field left, otherwise false
   */
  public boolean isFull() throws OutOfBoundsException {
    boolean emptyFieldFound = false;

    for (int i = 0; i < FIELDCOUNT && !emptyFieldFound; i ++) {
      if (!hasPieceAt(calcLocation(i))) {
        emptyFieldFound = true;
      }
    }

    return !emptyFieldFound;
  }

  /**
   * Returns the Piece at the given position
   *
   * @param  col                  Column number
   * @param  row                  Row number
   * @return                      Piece at the given position
   * @throws OutOfBoundsException thrown if the given position is not on the board
   */
  public Piece pieceAt(final Location location) throws OutOfBoundsException {
    if (isOutOfBounds(location)) {
      throw new OutOfBoundsException();
    }

    return fields[calcPos(location)];
  }

  /**
   * Checks if a piece is located at the given position
   *
   * @param  col                  Column number
   * @param  row                  Row number
   * @return                      true if a piece is located at the given position, otherwise false
   * @throws OutOfBoundsException thrown if the given position is not on the board
   */
  public boolean hasPieceAt(final Location location) throws OutOfBoundsException {
    return null != pieceAt(location);
  }

  /**
   * Checks if the given position is not on the board
   *
   * @param  col Column number
   * @param  row Row number
   * @return     true if the given position is on the board, otherwise false
   */
  public boolean isOutOfBounds(final Location location) {
    final int col = location.getColumn();
    final int row = location.getRow();

    return col < 0 || col >= BOARDLENGTH || row < 0 || row >= BOARDLENGTH;
  }

  private Location calcLocation(final int index) {
    return new Location(index % BOARDLENGTH, (int) index / BOARDLENGTH);
  }

  /**
   * Calculates the index
   *
   * @param  col Column number
   * @param  row Row number
   * @return     board index
   */
  private int calcPos(final Location location) {
    return location.getColumn() + location.getRow() * BOARDLENGTH;
  }
}
