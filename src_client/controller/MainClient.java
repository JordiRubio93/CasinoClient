package controller;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.SwingUtilities;

import tools.excepcions.TCPException;
import view.Dialeg;
import view.MainFrame;
import view.SplashScreen;

public class MainClient {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Manager manager = new Manager();
				//Si el servidor no hi es, no carrega y finilitza
				try {
					manager.checkServer();
				} catch (TCPException e) {
					new Dialeg().setWarningText("Server Disconected");
					System.exit(0);
				}
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
