package view;

import java.awt.CardLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import controller.Constants;
import controller.Manager;

public class MainFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	private CardLayout cardLayout;
	private ArrayList<BaseJPanel> panels;
	
	public MainFrame(ArrayList<BaseJPanel> panels){
		this.panels = panels;
		propietats();
		cardLayout = new CardLayout();
		getContentPane().setLayout(cardLayout);
		createCardLayout(panels);
	}
	
	private void propietats(){
		setTitle(Constants.PROJECT_NAME);
		setLocationRelativeTo(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void showPanel(String cad){System.out.println(cad);
		cardLayout.show(getContentPane(), "view." + cad);
		revalidate();
	}
	
	public void setManager(Manager manager){
		for(BaseJPanel panel : panels){
			panel.setManager(manager);
			panel.registerController();
		}
	}

	public void createCardLayout(ArrayList<BaseJPanel> panels){
		for(BaseJPanel panel : panels){
			getContentPane().add(panel, panel.getClass().getName().toString());
		}
	}
	
	public void showError(String string) {
		JOptionPane.showMessageDialog(this,string,"ERROR", JOptionPane.ERROR_MESSAGE);
	}

	public BaseJPanel getPanel(String cad){
		switch(cad){
		case "LoginWindow":
			return panels.get(6);
		case "MainWindow":
			return panels.get(5);
		case Constants.STATISTICS_VIEW_NAME:
			return panels.get(4);
		case Constants.BJ_VIEW_NAME:
			return panels.get(3);
		case Constants.H_VIEW_NAME:
			return panels.get(2);
		case Constants.R_VIEW_NAME:
			return panels.get(1);
		case Constants.GRAPHICS_VIEW_NAME:
			return panels.get(0);
		}
		return null;
	}
}
