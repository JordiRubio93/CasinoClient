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

package model;

/**
 * The Class Calcul.
 * (Calcula les posicions dels cavalls.)
 */
public class Calcul {

	/**
	 * Calcula Y.
	 *
	 * @param i
	 * @return int
	 */
	public static int calculaY(int i) {
		return i * 80 - 2;
	}

	/**
	 * Calcula X.
	 *
	 * @param sec
	 * @param ct
	 * @return int
	 */
	public static int calculaX(int sec, boolean ct) {
		return (sec + 10) * 5;
	}
}
