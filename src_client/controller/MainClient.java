package controller;

import javax.swing.SwingUtilities;

import view.MainFrame;
import view.SplashScreen;

public class MainClient {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				SplashScreen splash = new SplashScreen();
				MainFrame mainFrame = new MainFrame(splash.getPanels());
				Manager manager = new Manager(mainFrame);
				mainFrame.setManager(manager);
				mainFrame.setVisible(true);
			}
		});
	}
}
