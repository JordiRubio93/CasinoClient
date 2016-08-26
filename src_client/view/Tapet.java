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

import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * The Class Tapet.
 * (JPanel base per a pintar fons a través d'imatges.)
 */
public class Tapet extends JPanel {
	private static final long serialVersionUID = 1L;
	protected Image imatge;
	protected int w;
	protected int h;

	/**
	 * Instantiates a new tapet.
	 *
	 * @param layout
	 */
	public Tapet(LayoutManager layout) {
		setLayout(layout);
	}

	/**
	 * Instantiates a new tapet.
	 *
	 * @param w
	 * @param h
	 * @param ruta
	 */
	public Tapet(int w, int h, String ruta) {
		this.w = w;
		this.h = h;

		try {
			File fitxer = new File(ruta);
			imatge = ImageIO.read(fitxer);
		} catch (IOException e) {
			// e.printStackTrace();
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (imatge != null) {
			g.drawImage(imatge, 0, 0, w, h, null);
		}
	}

	/**
	 * Sets imatge.
	 *
	 * @param ruta
	 */
	public void setImatge(String ruta) {
		try {
			File fitxer = new File(ruta);
			imatge = ImageIO.read(fitxer);
			this.repaint();
		} catch (IOException e) {
			// e.printStackTrace();
		}
	}

	/**
	 * Sets w.
	 *
	 * @param w
	 */
	public void setW(int w) {
		this.w = w;
	}

	/**
	 * Sets h.
	 *
	 * @param h
	 */
	public void setH(int h) {
		this.h = h;
	}
}
