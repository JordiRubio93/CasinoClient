package model;

import javax.swing.SwingUtilities;

import controller.ButtonListener;
import view.LoginWindow;

public class Main {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// Creeem la vista
				LoginWindow mainWindow = new LoginWindow();
				
				ButtonListener controller = new ButtonListener(mainWindow);
				mainWindow.registerController(controller);
				mainWindow.setVisible(true);

			}
		});
	}
}
