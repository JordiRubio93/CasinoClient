package controller;

import javax.swing.SwingUtilities;
import view.PanellPrincipal;

public class MainClient {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// Creeem la vista
				PanellPrincipal view = new PanellPrincipal();
				Manager controller = new Manager(view);
				view.registerController(controller);
				view.setVisible(true);
			}
		});
	}
}
