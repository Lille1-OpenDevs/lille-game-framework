package gameframework.game;

import java.awt.Graphics;

public interface GameEntity {
	/** Defines the way an entity should be drawn and may use a SpriteManager
	 * @param g - Graphics
	 */
	public void draw(Graphics g);
	public boolean isMovable();
}
