package view;

import java.awt.Rectangle;

import javax.swing.JPanel;

import controller.Manager;
import model.Utilities;

public abstract class BaseJPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private Manager manager;
	protected int width;
	protected int height;
	
	public BaseJPanel(){
		Rectangle rectangle = Utilities.getUsableScreenBounds();
		this.width = (int) rectangle.getWidth();
		this.height = (int) rectangle.getHeight();
	}
	
	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager){
		this.manager = manager;
	}
	
	public abstract void registerController();
}
