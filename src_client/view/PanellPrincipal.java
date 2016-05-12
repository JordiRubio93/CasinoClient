package view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.Manager;
import java.awt.CardLayout;

public class PanellPrincipal extends JFrame{

	private static final long serialVersionUID = 1L;
	private BaseJPanel panel;
	private CardLayout cardLayout;
	private Manager manager;
	
	public PanellPrincipal(){
		setTitle("LS Casino");
		setLocationRelativeTo(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		cardLayout = new CardLayout(0,0);
		getContentPane().setLayout(cardLayout);
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(BaseJPanel panel) {
		this.panel = panel;
		panel.setManager(manager);
		getContentPane().add(panel, panel.getClass().getName().toString());
		cardLayout.show(getContentPane(), panel.getClass().getName().toString());
	}

	public void showError(String string) {
		JOptionPane.showMessageDialog(this,string,"ERROR", JOptionPane.ERROR_MESSAGE);
	}

	public void registerController(Manager manager) {
		this.manager = manager;
	}
}