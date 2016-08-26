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

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import view.horses.PickHorseView;

/**
 * The Class MouseController.
 * (Controlador per a posar colors a l'elector de cavalls.)
 */
public class MouseController implements MouseListener{
	private PickHorseView window;

	/**
	 * Sets window.
	 *
	 * @param window
	 */
	public void setWindow(PickHorseView window) {
		this.window = window;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {}
	
	@Override
	public void mouseEntered(MouseEvent arg0) {
		switch(((JButton)arg0.getSource()).getClientProperty("mouse").toString()){
		case "CENTRAL":
			window.pintaBoto(window.getCentral());
			break;
		case "R":
			window.pintaBoto(window.getR());
			break;
		case "L":
			window.pintaBoto(window.getL());
			break;
		}
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		switch(((JButton)arg0.getSource()).getClientProperty("mouse").toString()){
		case "CENTRAL":
			window.despintaBoto(window.getCentral());
			break;
		case "R":
			window.despintaBoto(window.getR());
			break;
		case "L":
			window.despintaBoto(window.getL());
			break;
		}
	}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}
}
