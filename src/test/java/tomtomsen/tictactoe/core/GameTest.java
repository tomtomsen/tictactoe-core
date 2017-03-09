package tomtomsen.tictactoe.core;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class GameTest {
    @Test
    public void IfGameStartsPlayerNeedToMakeAMove() throws Exception
    {
        Board board = new Board();
        final Player player1 = Mockito.mock(Player.class);
        final Player player2 = Mockito.mock(Player.class);
        final Renderer renderer = Mockito.mock(Renderer.class);

        final Location result = new Location(0, 0);
        Mockito.when(player1.makeMove(Mockito.any(Board.class))).thenReturn(result);
        Mockito.when(player2.giveUp()).thenReturn(true);

        Game game = new Game(player1, player2, board, renderer);
        game.run();

        Assert.assertEquals(Piece.CROSS, board.pieceAt(new Location(0, 0)));
        Mockito.verify(player1, Mockito.times(1)).makeMove(Mockito.any(Board.class));
    }

    @Test
    public void Player2WinsIfPlayer1GivesUp() throws Exception
    {
        Board board = new Board();
        final Player player1 = Mockito.mock(Player.class);
        final Player player2 = Mockito.mock(Player.class);
        final Renderer renderer = Mockito.mock(Renderer.class);

        Mockito.when(player1.giveUp()).thenReturn(true);

        Game game = new Game(player1, player2, board, renderer);
        game.run();

        Assert.assertFalse(game.isDraw());
        Assert.assertEquals(player2, game.getWinner());
        Mockito.verify(player2, Mockito.never()).makeMove(Mockito.any(Board.class));
    }

    @Test
    public void GameEndsInADraw() throws Exception
    {
        // X 0 X
        // 0 X 0
        // 0 X ?
        Board board = new Board();
        board.placePiece(Piece.CIRCLE, new Location(0, 0));
        board.placePiece(Piece.CROSS, new Location(1, 0));
        board.placePiece(Piece.CIRCLE, new Location(2, 0));
        board.placePiece(Piece.CROSS, new Location(0, 1));
        board.placePiece(Piece.CIRCLE, new Location(1, 1));
        board.placePiece(Piece.CROSS, new Location(2, 1));
        board.placePiece(Piece.CROSS, new Location(0, 2));
        board.placePiece(Piece.CIRCLE, new Location(1, 2));

        final Player player1 = Mockito.mock(Player.class);
        final Player player2 = Mockito.mock(Player.class);
        final Renderer renderer = Mockito.mock(Renderer.class);

        Mockito.when(player1.makeMove(Mockito.any(Board.class))).thenReturn(new Location(2, 2));

        Game game = new Game(player1, player2, board, renderer);
        game.run();

        Assert.assertTrue(board.isFull());
        Assert.assertTrue(game.isDraw());
        Assert.assertNull(game.getWinner());
        Mockito.verify(player2, Mockito.never()).makeMove(Mockito.any(Board.class));
    }

    @Test
    public void Player1Wins() throws Exception {
        // O X
        // X O
        //     O
        Board board = new Board();

        final Player player1 = Mockito.mock(Player.class);
        final Player player2 = Mockito.mock(Player.class);
        final Renderer renderer = Mockito.mock(Renderer.class);

        Mockito.when(player1.makeMove(Mockito.any(Board.class)))
                .thenReturn(new Location(0, 0))
                .thenReturn(new Location(1, 1))
                .thenReturn(new Location(2, 2));

        Mockito.when(player2.makeMove(Mockito.any(Board.class)))
                .thenReturn(new Location(0, 1))
                .thenReturn(new Location(1, 0));

        Game game = new Game(player1, player2, board, renderer);
        game.run();

        Assert.assertFalse(board.isFull());
        Assert.assertFalse(game.isDraw());
        Assert.assertEquals(player1, game.getWinner());
    }
}
