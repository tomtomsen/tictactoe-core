package tomtomsen.tictactoe.core;

/**
 * Renderer
 */
public interface Renderer {
  /**
   * Called if game gets updated
   *
   * @param game Game
   */
  void update(Game game);
  /**
    * Gets called if game has ended
    *
    * @param game Game
    */
  void gameEnded(Game game);
}
