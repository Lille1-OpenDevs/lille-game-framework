package gameframework.game;

import java.util.Collection;
import java.util.Iterator;

/**
 * stores all the gameframework.game entities of a gameframework.game level:
 * oneStepMoveAll() makes all the entities move ; overlapAll() manages all the
 * interactions between the entities.
 */
public interface GameUniverse {

	/**
	 * Add a game entity to the game universe
	 * @param gameEntity reference on the entity
	 */
	public void addGameEntity(GameEntity gameEntity);

	/**
	 * Remove a game entity from the game universe
	 * @param gameEntity reference on the entity
	 */
	public void removeGameEntity(GameEntity gameEntity);

	/**
	 * @return an iterator on the game entities
	 */
	public Iterator<GameEntity> getGameEntitiesIterator();

	/**
	 * Do a step on each game entities of the game universe as a game movable
	 */
	public void allOneStepMoves();

	/**
	 * process all over laps from overlap processor
	 */
	public void processAllOverlaps();

	/**
	 * Clear the entities of the game universe
	 */
	public void removeAllGameEntities();

	/**
	 * Remove a collection of game entity
	 * @param gameEntities the collection of each entities to remove from the game
	 */
	public void removeGameEntities(Collection<GameEntity> gameEntities);
}
