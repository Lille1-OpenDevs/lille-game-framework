package gameframework.game;

public interface Game {
	
	/** Starts the first level found in GameData
	 *  should go through each level
	 *  until conditions defined in a level implementation are met.
	 */
	public void start();

}
