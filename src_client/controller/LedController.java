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

import java.awt.Color;
import java.io.IOException;

import network.ServerComunication;
import network.segment.Running;
import view.MainWindow;
/**
 * The Class LedController.
 * (Fil d'execució amb socket propi per gestionar el LED que informa de l'estat de les sales de joc.)
 */
public class LedController implements Runnable {
	private MainWindow view;
	private ServerComunication sc;
	private boolean running;

	/**
	 * Instantiates a new led controller.
	 *
	 * @param view
	 * @param cf
	 */
	public LedController(MainWindow view, ConfigurationFile cf) {
		this.view = view;
		this.sc = new ServerComunication(view.getManager(), cf);
	}

	/**
	 * (Bucle que va encenent i apagant el LED.)
	 */
	@Override
	public void run() {
		try {
			sc.establirConnexio(Boolean.TRUE);
			while (true) {
				sc.enviarTrama(new Running(false, 1));
				running = ((Running) sc.obtenirTrama()).isRunning();
				if (running)
					view.setLEDColor(Color.GREEN);
				else
					view.setLEDColor(Color.RED);
				Thread.sleep(500);
			}
		} catch (IOException | InterruptedException e) {
			// e.printStackTrace();
		}
	}

	/**
	 * Gets sc.
	 *
	 * @return sc
	 */
	public ServerComunication getSC() {
		return sc;
	}
}
