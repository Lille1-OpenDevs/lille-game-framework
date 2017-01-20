package gameframework.gui;

import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class represents the menu bar where user can interact with
 *
 */
public class GameMenuBar {

	/**
	 * Method used to get the component representing a menu bar
	 * @return the component's bar menu
	 */
	public MenuBar getComponent() {
		MenuBar menuBar = new MenuBar();
		
		// Create a menu with the name of 'file'
		Menu file = new Menu("file");
		
		// Add an item to the menu : quit
		MenuItem quit = new MenuItem("quit");
		menuBar.add(file);

		// Quit is used to exit the game window
		quit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		file.add(quit);

		return menuBar;
	}

}
