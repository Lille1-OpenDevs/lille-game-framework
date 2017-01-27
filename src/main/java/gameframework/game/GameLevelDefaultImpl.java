package gameframework.game;

import gameframework.drawing.GameUniverseViewPort;

/**
 * Default implementation of a game level.
 *
 * When creating the level for your game, you should extend this class rather
 * than implementing the interface itself.
 */
public abstract class GameLevelDefaultImpl extends Thread implements GameLevel {
	/** The default "tick rate" used for game simulation. */
	private static final int DEFAULT_MINIMUM_DELAY_BETWEEN_GAME_CYCLES = 100;

	/** The "tick rate" used for game simulation. */
	protected final int minimumDelayBetweenCycles;

	/** The universe used within this level. */
	protected GameUniverse universe;

	/** The board used within this level. */
	protected GameUniverseViewPort gameBoard;

	/** The game data used within this level. */
	protected final GameData data;

	/** The size of the sprites. */
	protected final int spriteSize;

	/** Flag to trigger the game loop (false means continue playing). */
	protected boolean stopGameLoop;

	/**
	 * Initialize the level.
	 *
	 * This is where you should change the game's board background, spawn your
	 * entities and so on.
	 */
	protected abstract void init();

	/**
	 * Create a level with a specific game data to use.
	 *
	 * @param data
	 *            The game data to use.
	 */
	public GameLevelDefaultImpl(GameData data) {
		this(data, DEFAULT_MINIMUM_DELAY_BETWEEN_GAME_CYCLES);
	}

	/**
	 * Create a level with a specific game data and "tick rate" to use.
	 * 
	 * @param data
	 *            The game data to use.
	 * @param minimumDelayBetweenCycles
	 *            The "tick rate" to use.
	 */
	public GameLevelDefaultImpl(GameData data, int minimumDelayBetweenCycles) {
		this.data = data;
		this.spriteSize = data.getConfiguration().getSpriteSize();
		this.universe = data.getUniverse();
		this.minimumDelayBetweenCycles = minimumDelayBetweenCycles;
	}

	@Override
	/**
	 * Start the level (thread).
	 *
	 * This is probably not the best place to initialize the level (change the
	 * game's board background, add entities and such), consider doing that in
	 * the "init" method instead.
	 */
	public void start() {
		this.init();
		super.start();
		try {
			super.join();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	/**
	 * Run the level
	 *
	 * The thread performs it's job unless the level is ordered to be stopped
	 * (or the thread is interrupted).
	 */
	public void run() {
		stopGameLoop = false;

		// Main game loop
		while (!stopGameLoop && !this.isInterrupted()) {
			long start = System.currentTimeMillis();
			gameBoard.paint();
			universe.allOneStepMoves();
			universe.processAllOverlaps();

			long sleepTime = this.minimumDelayBetweenCycles - (System.currentTimeMillis() - start);
			if (sleepTime > 0) {
				try {
					Thread.sleep(sleepTime);
				} catch (InterruptedException e) {
					// That's ok, we just didn't manage to finish sleeping
				}
			}
		}
	}

	@Override
	/**
	 * Ends the level.
	 *
	 * This is the ideal place to free the universe of entities, switch to
	 * another level... Basically anything "end of level" related.
	 */
	public void end() {
		stopGameLoop = true;
	}
}
