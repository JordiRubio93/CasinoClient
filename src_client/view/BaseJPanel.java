




package view;

import java.awt.Rectangle;

import javax.swing.JPanel;

import model.Utilities;
import controller.Manager;

/**
 * The Class BaseJPanel.
 * (Panell base del qual heretaran els altres panells importants que conformaran el CardLayout.)
 */
public abstract class BaseJPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private Manager manager;
	protected int width;
	protected int height;

	/**
	 * Instantiates a new BaseJPanel.
	 */
	public BaseJPanel() {
		Rectangle rectangle = Utilities.getUsableScreenBounds();
		this.width = (int) rectangle.getWidth();
		this.height = (int) rectangle.getHeight();
	}

	/**
	 * Gets manager.
	 *
	 * @return manager
	 */
	public Manager getManager() {
		return manager;
	}

	/**
	 * Sets manager.
	 *
	 * @param manager
	 */
	public void setManager(Manager manager) {
		this.manager = manager;
	}

	/**
	 * Register controller.
	 */
	public abstract void registerController();

	/**
	 * Inits elements.
	 */
	protected abstract void initElements();
}
