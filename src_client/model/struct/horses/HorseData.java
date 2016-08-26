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

package model.struct.horses;

import java.io.Serializable;


/**
 * The Class HorseData.
 * (Conté les dades de cada cavall.)
 */
public class HorseData implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private String color;
	private int dorsal;
	private int segons;

	/**
	 * Instantiates a new horse data.
	 *
	 * @param name
	 * @param color
	 * @param dorsal
	 */
	public HorseData(String name, String color, int dorsal) {
		this.name = name;
		this.color = color;
		this.dorsal = dorsal;
	}

	/**
	 * Gets name.
	 *
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets name.
	 *
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets color.
	 *
	 * @return color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * Sets color.
	 *
	 * @param color
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * Gets dorsal.
	 *
	 * @return dorsal
	 */
	public int getDorsal() {
		return dorsal;
	}

	/**
	 * Sets dorsal.
	 *
	 * @param dorsal
	 */
	public void setDorsal(int dorsal) {
		this.dorsal = dorsal;
	}

	/**
	 * Gets segons.
	 *
	 * @return segons
	 */
	public int getSegons() {
		return segons;
	}

	/**
	 * Sets segons.
	 * (Indica la velocitat del cavall.)
	 *
	 * @param segonsç
	 */
	public void setSegons(int segons) {
		this.segons = segons;
	}
}
