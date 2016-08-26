/**
 * @author
 * Pol Vales - ls30599@salleurl.edu
 * Enric Marin - ls31308@salleurl.edu
 * Diego Bellino - ls30741@salleurl.edu
 * Jordi Rubio - ls31289@salleurl.edu
 * David Estepa - ls30622@salleurl.edu
 * DPO2 (Disseny i programacio orientats a objectes)
 * La Salle, Universitat Ramon Llull
 */

package view;

import java.awt.CardLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import controller.Constants;
import controller.Manager;
import controller.listeners.WindowController;

/**
 * The Class MainFrame.
 */
public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private CardLayout cardLayout;
	private ArrayList<BaseJPanel> panels;

	/**
	 * Instantiates a new main frame.
	 *
	 * @param panels
	 */
	public MainFrame(ArrayList<BaseJPanel> panels) {
		this.panels = panels;
		propietats();
		cardLayout = new CardLayout();
		getContentPane().setLayout(cardLayout);
		createCardLayout(panels);
	}

	/**
	 * (Estableix les propietats bàsiques de la finestra.)
	 */
	private void propietats() {
		setLocationRelativeTo(null);
		setTitle(Constants.PROJECT_NAME);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}

	/**
	 * Show panel.
	 *
	 * @param cad
	 */
	public void showPanel(String cad) {
		// System.out.println(cad);
		cardLayout.show(getContentPane(), "view." + cad);
		revalidate();
	}

	/**
	 * Sets manager.
	 *
	 * @param manager
	 */
	public void setManager(Manager manager) {
		for (BaseJPanel panel : panels) {
			panel.setManager(manager);
			panel.registerController();
		}
	}

	/**
	 * Creates card layout.
	 *
	 * @param panels
	 */
	public void createCardLayout(ArrayList<BaseJPanel> panels) {
		for (BaseJPanel panel : panels) {
			getContentPane().add(panel, panel.getClass().getName().toString());
		}
	}

	/**
	 * Show error.
	 *
	 * @param string
	 */
	public void showError(String string) {
		JOptionPane.showMessageDialog(this, string, "ERROR", JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * Gets panel.
	 *
	 * @param cad
	 * @return the panel
	 */
	public BaseJPanel getPanel(String cad) {
		switch (cad) {
		case Constants.CASH_EVO_VIEW_NAME:
			return panels.get(8);
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

	/**
	 * Register controller.
	 * (Afegeix el controlador de finestra.)
	 *
	 * @param wc	
	 */
	public void registerController(WindowController wc) {
		this.addWindowListener(wc);
	}
}
