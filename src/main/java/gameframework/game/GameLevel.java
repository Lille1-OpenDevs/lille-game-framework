package gameframework.game;

/**
 * Interface for game levels.
 *
 * Technically speaking, a "GameLevel" is just a thread whose initialize/simulate/free the level.
 */
public interface GameLevel extends Runnable {
	/** Start the level (aka thread). */
	public void start();

	/** Stop the level (aka thread). */
	public void end();
}
