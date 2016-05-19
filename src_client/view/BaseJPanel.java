package view;

import javax.swing.JPanel;

import controller.Manager;

public abstract class BaseJPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private Manager manager;

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager){
		this.manager = manager;
	}
	
	public abstract void registerController();
}
