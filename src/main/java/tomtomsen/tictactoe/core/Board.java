package tomtomsen.tictactoe.core;

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
  private Piece[] board;
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
    board = new Piece[FIELDCOUNT];
    for (int i = 0; i < FIELDCOUNT; i ++) {
      board[i] = Piece.NONE;
    }
  }

  /**
   * places a piece on a specific position
   *
   * @param  piece   Piece to be placed
   * @param  col     Column
   * @param  row     Row
   *
   * @throws Exception If a piece has already been placed at that position
   */
  public void placePiece(Piece piece, int col, int row) throws Exception {
    if (Piece.NONE != pieceAt(col, row)) {
      throw new Exception("already placed " + pieceAt(col, row));
    }

    board[calcPos(col, row)] = piece;
  }

  /**
   * Checks if the current board is empty
   *
   * @return true if board is empty, otherwise false
   */
  public boolean isEmpty() throws Exception {
    for (int i = 0; i < FIELDCOUNT; i ++) {
      if (hasPieceAt(calcCol(i), calcRow(i))) {
        return false;
      }
    }

    return true;
  }

  /**
   * Checks if the current board is full
   *
   * @return true if there is no empty field left, otherwise false
   */
  public boolean isFull() throws Exception {
    for (int i = 0; i < FIELDCOUNT; i ++) {
      if (!hasPieceAt(calcCol(i), calcRow(i))) {
        return false;
      }
    }

    return true;
  }

  /**
   * Returns the Piece at the given position
   *
   * @param  col       Column number
   * @param  row       Row number
   *
   * @return           Piece at the given position
   *
   * @throws Exception thrown if the given position is not on the board
   */
  public Piece pieceAt(int col, int row) throws Exception {
    if (isOutOfBounds(col, row)) {
      throw new Exception("out of bounds");
    }

    return board[calcPos(col, row)];
  }

  /**
   * Checks if a piece is located at the given position
   *
   * @param  col       Column number
   * @param  row       Row number
   *
   * @return           true if a piece is located at the given position, otherwise false
   *
   * @throws Exception thrown if the given position is not on the board
   */
  public boolean hasPieceAt(int col, int row) throws Exception {
    return Piece.NONE != pieceAt(col, row);
  }

  /**
   * Checks if the given position is not on the board
   *
   * @param  col Column number
   * @param  row Row number
   *
   * @return     true if the given position is on the board, otherwise false
   */
  public boolean isOutOfBounds(int col, int row) {
    return (col < 0 || col >= BOARDLENGTH || row < 0 || row >= BOARDLENGTH);
  }

  /**
   * Calculates the column number
   *
   * @param  i board index
   *
   * @return   the column number
   */
  private int calcCol(int i) {
    return i % BOARDLENGTH;
  }

  /**
   * Calculates the row number
   *
   * @param  i board index
   *
   * @return   the row number
   */
  private int calcRow(int i) {
    return (int) i / BOARDLENGTH;
  }

  /**
   * Calculates the index
   *
   * @param  col Column number
   * @param  row Row number
   *
   * @return     board index
   */
  private int calcPos(int col, int row) {
    return col + row * BOARDLENGTH;
  }
}
