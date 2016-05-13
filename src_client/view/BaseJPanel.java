package view;

import javax.swing.JPanel;

import controller.Manager;

public class BaseJPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private Manager manager;
	
	public BaseJPanel(){
		super();
	}
	
	public BaseJPanel(Manager manager) {
		super();
		this.manager = manager;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager){
		this.manager = manager;
	}
}
