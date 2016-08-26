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

package view.roulette;

import java.awt.Color;

import javax.swing.JButton;

/**
 * The Class MyButton.
 */
public class MyButton extends JButton {

	// Atributs de la classe
	private static final long serialVersionUID = 1L;
	private boolean pushed;
	private Color color;

	/**
	 * Instantiates a new my button.
	 *
	 * @param s
	 * @param color
	 */
	public MyButton(String s, Color color) {
		super(s);
		this.color = color;
		this.pushed = true;
	}// Tancament del constructor

	/**
	 * Canvia estat.
	 */
	public void canviaEstat() {
		if (pushed == false)
			pushed = true;
		else
			pushed = false;
	}// Tancament del metode

	/**
	 * Consulta estat.
	 *
	 * @return true, if successful
	 */
	public boolean consultaEstat() {
		return pushed;
	}// Tancament del metode

	/**
	 * Gets color.
	 *
	 * @return color
	 */
	public Color getColor() {
		return color;
	}// Tancament del metode
}
