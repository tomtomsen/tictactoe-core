package tomtomsen.tictactoe.core;

/**
 * Created by olmolmsen on 28.02.2017.
 */

public class Game {
    private Board board;
    private Player[] players;
    private Piece[] pieces = {Piece.CIRCLE, Piece.CROSS};
    private Player winner;
    private Renderer renderer;

    public Game(Player player1, Player player2, Board board, Renderer renderer) {
        players = new Player[2];
        players[0] = player1;
        players[1] = player2;

        this.board = board;
        this.renderer = renderer;
    }

    public Board getBoard() {
        return board;
    }

    public void run() throws Exception {
        int currentPlayer = 0;
        while (!board.isFull()) {
            if (players[currentPlayer].giveUp()) {
                declareWinner(players[(currentPlayer+1) % 2]);
                break;
            }

            int[] move = players[currentPlayer].makeMove(board, pieces[currentPlayer]);
            if (board.hasPieceAt(move[0], move[1])) {
                continue;
            }

            board.placePiece(pieces[currentPlayer], move[0], move[1]);
            renderer.update(this);

            if (winningCondition()) {
                declareWinner(players[currentPlayer]);
                break;
            }

            currentPlayer = (++currentPlayer % 2);
        }

        renderer.gameEnded(this);
    }

    protected boolean winningCondition() throws Exception {
        return (board.hasPieceAt(0, 0) && board.pieceAt(0, 0) == board.pieceAt(0, 1) && board.pieceAt(0, 1) == board.pieceAt(0, 2))
                || (board.hasPieceAt(1, 0) && board.pieceAt(1, 0) == board.pieceAt(1, 1) && board.pieceAt(1, 1) == board.pieceAt(2, 2))
                || (board.hasPieceAt(2, 0) && board.pieceAt(2, 0) == board.pieceAt(2, 1) && board.pieceAt(2, 1) == board.pieceAt(2, 2))
                || (board.hasPieceAt(0, 0) && board.pieceAt(0, 0) == board.pieceAt(1, 0) && board.pieceAt(1, 0) == board.pieceAt(2, 0))
                || (board.hasPieceAt(0, 1) && board.pieceAt(0, 1) == board.pieceAt(1, 1) && board.pieceAt(1, 1) == board.pieceAt(2, 1))
                || (board.hasPieceAt(0, 2) && board.pieceAt(0, 2) == board.pieceAt(1, 2) && board.pieceAt(1, 2) == board.pieceAt(2, 2))
                || (board.hasPieceAt(0, 0) && board.pieceAt(0, 0) == board.pieceAt(1, 1) && board.pieceAt(1, 1) == board.pieceAt(2, 2))
                || (board.hasPieceAt(0, 2) && board.pieceAt(0, 2) == board.pieceAt(1, 1) && board.pieceAt(1, 1) == board.pieceAt(2, 0))
                ;
    }

    protected void declareWinner(Player player) {
        winner = player;
    }

    public Player getWinner() {
        return winner;
    }

    public boolean isDraw() {
        return winner == null;
    }
}
