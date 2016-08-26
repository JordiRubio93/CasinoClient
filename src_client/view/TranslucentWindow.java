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

package view;

import java.awt.Color;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JWindow;

/**
 * The Class TranslucentWindow.
 * (Es tracta de la finestra del Splash Screen, i és un fil d'execució a part.)
 */
public class TranslucentWindow extends JWindow implements Runnable {
	private static final long serialVersionUID = 1L;

	@Override
	public void run() {
		setAlwaysOnTop(true);
		setBackground(new Color(0, 0, 0, 0));
		setContentPane(new TranslucentPane());
		Icon icon = new ImageIcon(controller.Constants.PATH_SPLASH);
		JLabel label = new JLabel(icon);
		add(label);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	/**
	 * Stop.
	 */
	public void stop() {
		dispose();
	}
}
