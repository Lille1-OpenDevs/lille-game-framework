package gameframework.game;

/**
 * A game level (also called map in gamer's language).
 *
 * A "GameLevel" is just a thread that initialize (see "start" method), simulate and free (see "end" method) the level.
 * This interface is used by the game's data (the GameData class) to manage all levels within the game.
 */
public interface GameLevel extends Runnable {
	/**
	 * Start the level (aka thread).
	 * This is where you should instanciate all the entities used within your level through GameUniverse and perform other "start of game/level" operations.
	 */
	public void start();

	/** Stop the level (aka thread).
	 * This is where you should free all the entities used within your level through GameUniverse and perform other "end of game/level" operations.
	 */
	public void end();
}
