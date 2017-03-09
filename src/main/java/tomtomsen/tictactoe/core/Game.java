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
  private final Piece[] pieces = {Piece.CROSS, Piece.CIRCLE};
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
    players[0].assignPiece(pieces[0]);

    players[1] = player2;
    players[1].assignPiece(pieces[1]);

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

      final Location location = players[currentPlayer].makeMove(board);
      if (board.isOutOfBounds(location) || board.hasPieceAt(location)) {
        continue;
      }

      board.placePiece(pieces[currentPlayer], location);
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
    return board.hasPieceAt(new Location(0, 0))
            && board.pieceAt(new Location(0, 0)) == board.pieceAt(new Location(0, 1))
            && board.pieceAt(new Location(0, 1)) == board.pieceAt(new Location(0, 2))
        || board.hasPieceAt(new Location(1, 0))
            && board.pieceAt(new Location(1, 0)) == board.pieceAt(new Location(1, 1))
            && board.pieceAt(new Location(1, 1)) == board.pieceAt(new Location(2, 2))
        || board.hasPieceAt(new Location(2, 0))
            && board.pieceAt(new Location(2, 0)) == board.pieceAt(new Location(2, 1))
            && board.pieceAt(new Location(2, 1)) == board.pieceAt(new Location(2, 2))
        || board.hasPieceAt(new Location(0, 0))
            && board.pieceAt(new Location(0, 0)) == board.pieceAt(new Location(1, 0))
            && board.pieceAt(new Location(1, 0)) == board.pieceAt(new Location(2, 0))
        || board.hasPieceAt(new Location(0, 1))
            && board.pieceAt(new Location(0, 1)) == board.pieceAt(new Location(1, 1))
            && board.pieceAt(new Location(1, 1)) == board.pieceAt(new Location(2, 1))
        || board.hasPieceAt(new Location(0, 2))
            && board.pieceAt(new Location(0, 2)) == board.pieceAt(new Location(1, 2))
            && board.pieceAt(new Location(1, 2)) == board.pieceAt(new Location(2, 2))
        || board.hasPieceAt(new Location(0, 0))
            && board.pieceAt(new Location(0, 0)) == board.pieceAt(new Location(1, 1))
            && board.pieceAt(new Location(1, 1)) == board.pieceAt(new Location(2, 2))
        || board.hasPieceAt(new Location(0, 2))
            && board.pieceAt(new Location(0, 2)) == board.pieceAt(new Location(1, 1))
            && board.pieceAt(new Location(1, 1)) == board.pieceAt(new Location(2, 0))
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
