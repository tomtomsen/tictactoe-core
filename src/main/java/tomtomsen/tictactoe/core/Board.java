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
  public void placePiece(final Piece piece, final int col, final int row)
    throws OutOfBoundsException, FieldBlockedException {

    if (null != pieceAt(col, row)) {
      throw new FieldBlockedException(pieceAt(col, row));
    }

    fields[calcPos(col, row)] = piece;
  }

  /**
   * Checks if the current board is empty
   *
   * @return true if board is empty, otherwise false
   */
  public boolean isEmpty() throws OutOfBoundsException {
    boolean pieceFound = false;

    for (int i = 0; i < FIELDCOUNT && !pieceFound; i ++) {
      if (hasPieceAt(calcCol(i), calcRow(i))) {
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
      if (!hasPieceAt(calcCol(i), calcRow(i))) {
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
  public Piece pieceAt(final int col, final int row) throws OutOfBoundsException {
    if (isOutOfBounds(col, row)) {
      throw new OutOfBoundsException();
    }

    return fields[calcPos(col, row)];
  }

  /**
   * Checks if a piece is located at the given position
   *
   * @param  col                  Column number
   * @param  row                  Row number
   * @return                      true if a piece is located at the given position, otherwise false
   * @throws OutOfBoundsException thrown if the given position is not on the board
   */
  public boolean hasPieceAt(final int col, final int row) throws OutOfBoundsException {
    return null != pieceAt(col, row);
  }

  /**
   * Checks if the given position is not on the board
   *
   * @param  col Column number
   * @param  row Row number
   * @return     true if the given position is on the board, otherwise false
   */
  public boolean isOutOfBounds(final int col, final int row) {
    return col < 0 || col >= BOARDLENGTH || row < 0 || row >= BOARDLENGTH;
  }

  /**
   * Calculates the column number
   *
   * @param  index board index
   * @return       the column number
   */
  private int calcCol(final int index) {
    return index % BOARDLENGTH;
  }

  /**
   * Calculates the row number
   *
   * @param  index board index
   * @return       the row number
   */
  private int calcRow(final int index) {
    return (int) index / BOARDLENGTH;
  }

  /**
   * Calculates the index
   *
   * @param  col Column number
   * @param  row Row number
   * @return     board index
   */
  private int calcPos(final int col, final int row) {
    return col + row * BOARDLENGTH;
  }
}
