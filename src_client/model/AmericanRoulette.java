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

import java.awt.Color;
import java.util.ArrayList;

import model.struct.roulette.Casella;

/**
 * The Class AmericanRoulette.
 * (Conté les dades pels botons de la ruleta.)
 */
public class AmericanRoulette {
	// Atributs de la classe
	String[] num_casella = { "3", "6", "9", "12", "15", "18", "21", "24", "27", "30", "33", "36", "2", "5", "8", "11",
			"14", "17", "20", "23", "26", "29", "32", "35", "1", "4", "7", "10", "13", "16", "19", "22", "25", "28",
			"31", "34" };

	Boolean[] color_caselles = { true, false, true, true, false, true, true, false, true, true, false, true, false,
			true, false, false, true, false, false, true, false, false, true, false, true, false, true, false, false,
			true, false, false, true, true, false, true };

	private final int rows = 3;
	private final int column = 12;
	private final int totalNumbers = (rows * column);
	private static ArrayList<Casella> caselles;

	/**
	 * Instantiates a new american roulette.
	 */
	public AmericanRoulette() {
		setCaselles();
	}// Tancament del constructor

	/**
	 * Sets caselles.
	 *
	 * @param caselles
	 */
	public void setCaselles(String[] caselles) {
		this.num_casella = caselles;
	}// Tancament del setter

	/**
	 * Gets color caselles.
	 *
	 * @return the color caselles
	 */
	public Boolean[] getColorCaselles() {
		return color_caselles;
	}// Tancament del getter

	/**
	 * Sets color caselles.
	 *
	 * @param color caselles
	 */
	public void setColorCaselles(Boolean[] color_caselles) {
		this.color_caselles = color_caselles;
	}// Tancament del setter

	/**
	 * Sets caselles.
	 */
	public void setCaselles() {
		caselles = new ArrayList<model.struct.roulette.Casella>();
		for (int i = 0; i < totalNumbers; i++) {
			caselles.add(new model.struct.roulette.Casella(Integer.parseInt(num_casella[i]), color_caselles[i]));
		}
	}// Tancament del setter

	/**
	 * Gets caselles.
	 *
	 * @return caselles
	 */
	public ArrayList<Casella> getCaselles() {
		return caselles;
	}// Tancament del getter

	/**
	 * (Obté el color d'una casella determinada.)
	 * 
	 * @param num
	 * @return color
	 */
	public static Color getColorCasella(int num){
		int index = 0;
		for(index = 0; caselles.get(index).getNumero() != num && index < caselles.size(); index++);
		return caselles.get(index).getColor(true);
	}
}// Tancament de la classe
