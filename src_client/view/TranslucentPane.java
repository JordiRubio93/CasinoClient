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

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

/**
 * The Class TranslucentPane.
 * (Panell incrustat en l'Splash Screen.)
 */
public class TranslucentPane extends JPanel {
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new translucent pane.
	 */
	public TranslucentPane() {
		setOpaque(false);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setComposite(AlphaComposite.SrcOver.derive(0.05f));
		g2d.setColor(getBackground());
		g2d.fillRect(0, 0, getWidth(), getHeight());
	}
}