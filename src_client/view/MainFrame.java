package view;

import java.awt.CardLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import controller.Constants;
import controller.Manager;
/**
 * 
 * <p>
 * <b> Classe: MainFrame </b> <br/>
 * </p>
 * Finestra principal que contindr‡ tots els panells de l'aplicaciÛ
 * 
 * @version 1.0 19/05/2016
 * @author  Pol Val√©s - ls30599@salleurl.edu <br/>
 * 			Diego Bellino - ls30741@salleurl.edu <br/>
 * 			Enric Marin - ls31308@salleurl.edu <br/>
 * 			Jordi Rubi√≥ - ls31289@salleurl.edu <br/>
 * 			David Estepa - ls30622@salleurl.edu <br/>
 * 			Disseny i programaci√≥ orientats a objectes. <br/>
 * 			La Salle - Universitat Ramon Llull. <br/>
 * 
 */
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
		setLocationRelativeTo(null);
		setTitle(Constants.PROJECT_NAME);
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
		case Constants.CASH_RANKING_VIEW_NAME:
			return panels.get(7);
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
