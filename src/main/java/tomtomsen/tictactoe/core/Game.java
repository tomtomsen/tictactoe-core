package tomtomsen.tictactoe.core;

import tomtomsen.tictactoe.core.exception.OutOfBoundsException;

/**
 * Game logic
 */
public class Game {
  /**
   * Board
   */
  private final Board board;
  /**
   * Players
   */
  private final Player[] players;
  /**
   * Pieces
   */
  private final Piece[] pieces = {Piece.CIRCLE, Piece.CROSS};
  /**
   * Winner
   */
  private Player winner;
  /**
   * Renderer
   */
  private final Renderer renderer;

  /**
   * Initializes the Game
   *
   * @param  player1  [description]
   * @param  player2  [description]
   * @param  board  [description]
   * @param  renderer [description]
   */
  public Game(
      final Player player1,
      final Player player2,
      final Board board,
      final Renderer renderer) {
    players = new Player[2];
    players[0] = player1;
    players[1] = player2;

    this.board = board;
    this.renderer = renderer;
  }

  /**
   * Returns the current board
   *
   * @return current board
   */
  public Board getBoard() {
    return board;
  }

  /**
   * Runs the game
   */
  public void run() throws OutOfBoundsException {
    int currentPlayer = 0;
    while (!board.isFull()) {
      if (players[currentPlayer].giveUp()) {
        declareWinner(players[(currentPlayer + 1) % 2]);
        break;
      }

      final int[] move = players[currentPlayer].makeMove(board, pieces[currentPlayer]);
      if (board.isOutOfBounds(move[0], move[1]) || board.hasPieceAt(move[0], move[1])) {
        continue;
      }

      board.placePiece(pieces[currentPlayer], move[0], move[1]);
      renderer.update(this);

      if (winningCondition()) {
        declareWinner(players[currentPlayer]);
        break;
      }

      currentPlayer = ++currentPlayer % 2;
    }

    renderer.gameEnded(this);
  }

  /**
   * Checks if there are three pieces in a row
   *
   * @return true if the game should end
   */
  protected boolean winningCondition() throws OutOfBoundsException {
    return board.hasPieceAt(0, 0)
            && board.pieceAt(0, 0) == board.pieceAt(0, 1)
            && board.pieceAt(0, 1) == board.pieceAt(0, 2)
        || board.hasPieceAt(1, 0)
            && board.pieceAt(1, 0) == board.pieceAt(1, 1)
            && board.pieceAt(1, 1) == board.pieceAt(2, 2)
        || board.hasPieceAt(2, 0)
            && board.pieceAt(2, 0) == board.pieceAt(2, 1)
            && board.pieceAt(2, 1) == board.pieceAt(2, 2)
        || board.hasPieceAt(0, 0)
            && board.pieceAt(0, 0) == board.pieceAt(1, 0)
            && board.pieceAt(1, 0) == board.pieceAt(2, 0)
        || board.hasPieceAt(0, 1)
            && board.pieceAt(0, 1) == board.pieceAt(1, 1)
            && board.pieceAt(1, 1) == board.pieceAt(2, 1)
        || board.hasPieceAt(0, 2)
            && board.pieceAt(0, 2) == board.pieceAt(1, 2)
            && board.pieceAt(1, 2) == board.pieceAt(2, 2)
        || board.hasPieceAt(0, 0)
            && board.pieceAt(0, 0) == board.pieceAt(1, 1)
            && board.pieceAt(1, 1) == board.pieceAt(2, 2)
        || board.hasPieceAt(0, 2)
            && board.pieceAt(0, 2) == board.pieceAt(1, 1)
            && board.pieceAt(1, 1) == board.pieceAt(2, 0)
        ;
  }

  /**
   * Declares a player to be the winner
   *
   * @param player winning player
   */
  protected void declareWinner(final Player player) {
    winner = player;
  }

  /**
   * Returns the winner
   *
   * @return winner
   */
  public Player getWinner() {
    return winner;
  }

  /**
   * Returns if game is in draw
   *
   * @return true if game is draw, otherwise false
   */
  public boolean isDraw() {
    return winner == null;
  }
}
