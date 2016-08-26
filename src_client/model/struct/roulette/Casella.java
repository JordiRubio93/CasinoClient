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

package model.struct.roulette;

import java.awt.Color;

/**
 * The Class Casella.
 */
public class Casella {
	// Atributs de la classe
	private int numero;
	private boolean color;

	/**
	 * Gets número.
	 *
	 * @return número
	 */
	public int getNumero() {
		return numero;
	}// Tancament del getter

	/**
	 * Gets color.
	 *
	 * @return color
	 */
	public Color getColor(boolean dialog) {
		if(dialog){
			if(numero == 0) return new Color(152,251,152);
			else if(color) return new Color(255,99,71);
			else return new Color(169,169,169);
		}else{
			if (color) return (new Color(139, 0, 0));
			else return (new Color(010, 010, 010));
		}
	}// Tancament del getter

	/**
	 * Instantiates a new casella.
	 *
	 * @param numero
	 * @param color
	 */
	public Casella(int numero, boolean color) {
		super();
		this.numero = numero;
		this.color = color;

	}// Tancament del constructor

	/**
	 * Checks if is parell.
	 *
	 * @return true, if is parell
	 */
	public boolean isParell() {
		return numero % 2 == 0;
	}// Tancament del metode

	/**
	 * Checks if is vermell.
	 *
	 * @return true, if is vermell
	 */
	public boolean isVermell() {
		return color;
	}

	/**
	 * Checks if is negre.
	 *
	 * @return true, if is negre
	 */
	public boolean isNegre() {
		return color;
	}// Tancament del metode

	/**
	 * Checks if is senar.
	 *
	 * @return true, if is senar
	 */
	public boolean isSenar() {
		return numero % 2 != 0;
	}// Tancament del metode

	/**
	 * Checks if is manca.
	 *
	 * @return true, if is manca
	 */
	public boolean isManca() {
		return numero <= 18;
	}// Tancament del metode

	/**
	 * Checks if is pasa.
	 *
	 * @return true, if is pasa
	 */
	public boolean isPasa() {
		return numero > 18;
	}// Tancament del metode

	/**
	 * (Retorna a la dotzena que pertany.)
	 *
	 * @return int
	 */
	public int Dotzena() {
		if (numero > 0 && numero <= 12)
			return 1;
		if (numero > 12 && numero <= 24)
			return 2;
		if (numero > 24 && numero <= 36)
			return 3;
		if (numero == 0)
			return 0;

		return (-1);
	}// Tancament del metode
}// Tancament de la classe
