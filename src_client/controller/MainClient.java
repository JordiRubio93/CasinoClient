package controller;

import javax.swing.SwingUtilities;

import view.MainFrame;

public class MainClient {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// Creeem la vista
				MainFrame view = new MainFrame();
				Manager controller = new Manager(view);
				view.registerController(controller);
				view.setVisible(true);
			}
		});
	}
}
