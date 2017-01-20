package gameframework.game;

import gameframework.drawing.GameCanvas;
import gameframework.drawing.GameCanvasDefaultImpl;
import gameframework.motion.blocking.MoveBlockerChecker;
import gameframework.motion.blocking.MoveBlockerCheckerDefaultImpl;
import gameframework.motion.blocking.MoveBlockerRulesApplier;
import gameframework.motion.blocking.MoveBlockerRulesApplierDefaultImpl;
import gameframework.motion.overlapping.OverlapProcessor;
import gameframework.motion.overlapping.OverlapProcessorDefaultImpl;
import gameframework.motion.overlapping.OverlapRulesApplier;
import gameframework.motion.overlapping.OverlapRulesApplierDefaultImpl;

public class GameConfiguration {
	
	protected final int nbRows;
	protected final int nbColumns;
	protected final int spriteSize;
	protected final int nbLives;

	private static final int DEFAULT_NBROWS = 31;
	private static final int DEFAULT_NBCOL = 28;
	private static final int DEFAULT_SPRITESIZE = 16;
	private static final int DEFAULT_NBLIVES = 2;

	// CONSTRUCTORS
	
	/**
	 * Constructor with parameterisable size. Create a new GameConfiguration with the specified
	 * parameters. If 0 is specified as one of the parameter, the constructor uses the default
	 * value for this parameter. 
	 * @param nbRows The number of rows of the window (default value: {@value #DEFAULT_NBROWS}).
	 * @param nbColumns The number of columns of the window (default value: {@value #DEFAULT_NBCOL}). 
	 * @param spriteSize The size of the sprites displayed (default value: {@value #DEFAULT_SPRITESIZE}).
	 * @param nbLives The number of lives of the player (default value: {@value #DEFAULT_NBLIVES}).
	 */
	public GameConfiguration(int nbRows, int nbColumns, int spriteSize,
			int nbLives) {

		this.nbRows = nbRows <= 0
					? DEFAULT_NBROWS
					: nbRows;

		this.nbColumns = nbColumns <= 0
					 ? DEFAULT_NBCOL
					 : nbColumns;

		this.spriteSize = spriteSize <= 0
						? DEFAULT_SPRITESIZE
						: spriteSize;
		
		this.nbLives = nbLives <= 0
					 ? DEFAULT_NBLIVES
					 : nbLives;
	}

	/**
	 * Default constructor which construct a game configruation from default parameters
	 */
	public GameConfiguration() {
		this(DEFAULT_NBROWS, DEFAULT_NBCOL, DEFAULT_SPRITESIZE, DEFAULT_NBLIVES);
	}

	// METHODS

	/**
	 * @return the number of row from the game configuration
	 */
	public int getNbRows() {
		return nbRows;
	}

	/**
	 * @return the number of column from the game configuration
	 */
	public int getNbColumns() {
		return nbColumns;
	}

	/**
	 * @return the general size of sprite from the game configuration
	 */
	public int getSpriteSize() {
		return spriteSize;
	}

	/**
	 * @return the default number of lives feom the game configuration
	 */
	public int getDefaultNbLives() {
		return nbLives;
	}

	/**
	 * @return a new game canvas
	 */
	public GameCanvas createCanvas() {
		return new GameCanvasDefaultImpl();
	}

	/**
	 * @return a new move blocker rules applier
	 */
	public MoveBlockerRulesApplier createMoveBlockerRulesApplier() {
		return new MoveBlockerRulesApplierDefaultImpl();
	}

	/**
	 * @return a new move blocker checker
	 */
	public MoveBlockerChecker createMoveBlockerChecker() {
		return new MoveBlockerCheckerDefaultImpl();
	}

	/**
	 * @return a new overlap rules applier
	 */
	public OverlapRulesApplier createOverlapRulesApplier() {
		return new OverlapRulesApplierDefaultImpl();
	}

	/**
	 * @return a new overlap processor
	 */
	public OverlapProcessor createOverlapProcessor() {
		return new OverlapProcessorDefaultImpl();
	}

	/**
	 * @return a new universe from the game configuration
	 */
	public GameUniverse createUniverse() {
		return createUniverse(new GameData(this));
	}

	/**
	 * Create a game universe from a specific game data
	 * @param gameData the specified game data
	 * @return a new game universe
	 */
	public GameUniverse createUniverse(GameData gameData) {
		return new GameUniverseDefaultImpl(gameData);
	}
}

