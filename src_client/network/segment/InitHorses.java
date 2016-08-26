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

package network.segment;

import java.util.LinkedList;

import model.struct.horses.HorseData;

/**
 * The Class InitHorses.
 */
public class InitHorses extends Segment {
	private static final long serialVersionUID = 1L;
	private LinkedList<HorseData> dades;
	private double guanys;

	/**
	 * Instantiates a new InitHorses segment.
	 *
	 * @param dades
	 * @param guanys
	 */
	public InitHorses(LinkedList<HorseData> dades, double guanys) {
		this.guanys = guanys;
		this.dades = dades;
	}

	/**
	 * Gets guanys.
	 *
	 * @return guanys
	 */
	public double getGuanys() {
		return guanys;
	}

	/**
	 * Gets dades.
	 *
	 * @return dades
	 */
	public LinkedList<HorseData> getDades() {
		return dades;
	}

	/**
	 * Sets dades.
	 *
	 * @param dades
	 */
	public void setDades(LinkedList<HorseData> dades) {
		this.dades = dades;
	}

	/**
	 * Sets guanys.
	 *
	 * @param guanys
	 */
	public void setGuanys(double guanys) {
		this.guanys = guanys;
	}
}
