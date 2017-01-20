package gameframework.gui;

import java.awt.Container;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

/**
 * This class represents the status of the bar contained into the window
 *
 */
public class GameStatusBar implements Observer {

	/**
	 * Contains the elements in the bar 
	 */
	protected final ArrayList<GameStatusBarElement<?>> elements = new ArrayList<>();

	
	/**
	 * Method used to get the container of the game status bar
	 * @return container
	 */
	public Container getContainer() {
		JPanel container = new JPanel();
		GridBagLayout layout = new GridBagLayout();
		container.setLayout(layout);

		for (GameStatusBarElement<?> element : elements) {
			container.add(element.getElementText());
			container.add(element.getElementValue());
		}

		update();
		return container;
	}

	/**
	 * This method adds an element to the game bar status
	 * @param newElement the element to add to the game bar status
	 */
	public void add(GameStatusBarElement<?> newElement) {
		this.elements.add(newElement);
		newElement.addObserver(this);
	}

	/**
	 * This method adds a list of elements to the game bar status
	 * @param elementsStatusBar some elements to add to the game bar status
	 */
	public void addAll(GameStatusBarElement<?>... elementsStatusBar) {
		for (GameStatusBarElement<?> element : elementsStatusBar) {
			this.add(element);
		}
	}

	public void update() {
		for (GameStatusBarElement<?> element : elements) {
			element.update();
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		update();
	}

}
