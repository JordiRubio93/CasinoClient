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

package view.statistics;

import java.awt.Color;

/**
 * The Class Bar.
 * (Per les barres del Top 5.)
 */
public class Bar {
	private double value;
	private Color color;
	private String name;

	/**
	 * Instantiates a new bar.
	 *
	 * @param value
	 * @param color
	 * @param name
	 */
	public Bar(int value, Color color, String name) {
		this.value = value;
		this.color = color;
		this.name = name;
	}

	/**
	 * Gets value.
	 *
	 * @return value
	 */
	public double getValue() {
		return value;
	}

	/**
	 * Sets value.
	 *
	 * @param value
	 */
	public void setValue(double value) {
		this.value = value;
	}

	/**
	 * Gets color.
	 *
	 * @return color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Sets color.
	 *
	 * @param color
	 */
	public void setColor(Color color) {
		this.color = color;
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
}
