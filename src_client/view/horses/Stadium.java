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

package view.horses;

import java.awt.Graphics;
import java.awt.Point;
import java.util.LinkedList;

import view.Tapet;
import controller.Constants;

/**
 * The Class Stadium.
 * (Classe que hereta de Tapet, amb característiques similars.)
 */
public class Stadium extends Tapet {
	private static final long serialVersionUID = 1L;
	private LinkedList<HorseAnimation> list;
	private Point[] coord;
	private boolean ready;

	/**
	 * Instantiates a new stadium.
	 *
	 * @param w
	 * @param h
	 * @param ruta
	 */
	public Stadium(int w, int h, String ruta) {
		super(w, h, ruta);
	}

	/**
	 * Sets list.
	 *
	 * @param aniList
	 */
	public void setList(LinkedList<HorseAnimation> aniList) {
		this.list = aniList;
	}

	/**
	 * Gets list.
	 *
	 * @return list
	 */
	public LinkedList<HorseAnimation> getList() {
		return list;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (ready) {
			for (int i = 0; i < Constants.nHorses; i++) {
				g.drawImage(list.get(i).getGif(), (int) coord[i].getX(), (int) coord[i].getY(), null);
				this.repaint();
			}
		}
	}

	/**
	 * Sets ready.
	 *
	 * @param ready
	 */
	public void setReady(boolean ready) {
		this.ready = ready;
	}

	/**
	 * Sets coordinates list.
	 *
	 * @param cList
	 */
	public void setCoordList(Point[] cList) {
		this.coord = cList;
	}

	/**
	 * Sets coordinates.
	 *
	 * @param x
	 * @param y
	 * @param i
	 */
	public void setCoord(int x, int y, int i) {
		coord[i].setLocation(coord[i].getX() + x, y);
	}

	/**
	 * Sets dimensions.
	 */
	public void setDimensions() {
		super.setW(this.getWidth());
		super.setH(this.getHeight());
	}
}
