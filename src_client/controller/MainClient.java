package controller;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.SwingUtilities;

import view.MainFrame;
import view.SplashScreen;

public class MainClient {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Manager manager = new Manager();
				SplashScreen splash = new SplashScreen(manager);
				MainFrame mainFrame = new MainFrame(splash.getPanels());
				mainFrame.setManager(manager);
				manager.setMainFrame(mainFrame);
				new Timer().schedule(new TimerTask() {
		            @Override
			        public void run() {
						manager.startGame();
						mainFrame.setVisible(true);
						splash.getTranslucentWindow().stop();
						
			        }
			    }, Constants.SPLASH_TIME);
			}
		});
	}
}
