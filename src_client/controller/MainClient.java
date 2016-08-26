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

package controller;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.SwingUtilities;

import controller.listeners.MyMusic;
import controller.listeners.WindowController;
import tools.excepcions.TCPException;
import view.Dialeg;
import view.MainFrame;
import view.MainWindow;
import view.SplashScreen;

/**
 * The Class MainClient.
 */
public class MainClient {

	/**
	 * The main method.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Thread(new MyMusic()).start();

				Manager manager = new Manager();
				WindowController wc = new WindowController(manager);
				manager.setWindow(wc);
				// Si el servidor no hi es, no carrega y finilitza
				try {
					manager.checkServer();
				} catch (TCPException e) {
					new Dialeg().setWarningText("Server Disconected");
					System.exit(0);
				}
				SplashScreen splash = new SplashScreen();
				MainFrame mainFrame = new MainFrame(splash.getPanels());
				mainFrame.registerController(wc);
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

				LedController ledController = new LedController((MainWindow) manager.getPanel(Constants.MAIN_VIEW_NAME),
						manager.getCf());
				new Thread(ledController).start();
				manager.setLedController(ledController);
			}
		});
	}// Tancament del main
}// Tancament de la classe
