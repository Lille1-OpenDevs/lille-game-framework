package gameframework.gui;

import gameframework.drawing.GameCanvas;
import gameframework.game.GameConfiguration;
import gameframework.game.GameData;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.awt.Component;

/**
 * This class represents a single window of the game : the visible part for the player
 * It makes a frame appear onto the screen 
 */
public class GameWindow {

	//FIELDS
	protected final Frame frame;
	protected GameCanvas gameCanvas;
	protected final GameStatusBar statusBar = new GameStatusBar();
	
	// CONSTRUCTORS
	public GameWindow(String gameName, GameCanvas gameCanvas, GameData data) {
		this(gameName, gameCanvas, data.getConfiguration(),
				new GameStatusBarElement<>("Score:", data.getScore()),
				new GameStatusBarElement<>("Life:", data.getLife()));
	}

	public GameWindow(String gameName, GameCanvas gameCanvas,
			GameConfiguration configuration,
			GameStatusBarElement<?>... elementsStatusBar) {
		if (gameCanvas == null) {
			throw new IllegalArgumentException("gameCanvas is null");
		}
		this.statusBar.addAll(elementsStatusBar);
		this.frame = new Frame(gameName);
		this.gameCanvas = gameCanvas;
		this.gameCanvas.setSize(//
				configuration.getSpriteSize() * configuration.getNbColumns(), //
				configuration.getSpriteSize() * configuration.getNbRows());
	}

	/**
	 * This method creates a game user interface
	 */
	public void createGUI() {
		frame.dispose();
		frame.setMenuBar(new GameMenuBar().getComponent());
		gameCanvas.addTo(frame);
		frame.add(this.statusBar.getContainer(), BorderLayout.NORTH);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	/**
	 * This method add a component to the frame 
	 * @param comp the component to add to the frame
	 * @param layout the layout to use for the new component
	 */
	public void add(Component comp, String layout) {
		this.frame.add(comp, layout);
		this.frame.pack();
	}

}
