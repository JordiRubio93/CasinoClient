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

package controller.listeners;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import controller.Manager;

/**
 * The Class WindowController.
 * (Executa les accions corresponents que s'han de fer quan es tanca la finestra per la X.)
 */
public class WindowController extends WindowAdapter {
	private Manager manager;

	/**
	 * Instantiates a new window controller.
	 *
	 * @param manager
	 */
	public WindowController(Manager manager) {
		this.manager = manager;
	}

	/**
	 * (Segons la finestra que toqui, realitzarà unes accions o altres:
	 * - Si és la principal, desconnectarà a l'usuari.
	 * - Si no ho és, tornarà a la principal.)
	 */
	@Override
	public void windowClosing(WindowEvent event) {
		if (event.getSource().equals(manager.getView())) {
			try {
				manager.getServer().tancarConnexio(false);
				manager.getLedController().getSC().tancarConnexio(true);
			} catch (IOException e) {
				//System.err.println("No he pogut tancar la connexió... hi havia?");
			} finally {
				System.exit(0);
			}
		} else {
			event.getWindow().setVisible(false);
			event.getWindow().dispose();
			manager.getView().setEnabled(true);
			manager.getView().setVisible(true);
		}
	}
}
