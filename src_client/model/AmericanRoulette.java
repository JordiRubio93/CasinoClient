package model;

import java.util.ArrayList;

import model.struct.roulette.Casella;
/**
 * 
 * <p>
 * <b> Classe: AmericanRoulette </b> <br/>
 * </p>
 * 
 * @version 1.0 19/05/2016
 * @author  Pol Valés - ls30599@salleurl.edu <br/>
 * 			Diego Bellino - ls30741@salleurl.edu <br/>
 * 			Enric Marin - ls31308@salleurl.edu <br/>
 * 			Jordi Rubió - ls31289@salleurl.edu <br/>
 * 			David Estepa - ls30622@salleurl.edu <br/>
 * 			Disseny i programació orientats a objectes. <br/>
 * 			La Salle - Universitat Ramon Llull. <br/>
 * 
 */
public class AmericanRoulette {
	//Atributs de la classe
	String[] num_casella = { "3", "6", "9", "12", "15", "18", "21", "24", "27", "30", "33", "36", "2", "5", "8", "11",
			"14", "17", "20", "23", "26", "29", "32", "35", "1", "4", "7", "10", "13", "16", "19", "22", "25", "28",
			"31", "34" };

	Boolean[] color_caselles = { true, false, true, true, false, true, true, false, true, true, false, true, false,
			true, false, false, true, false, false, true, false, false, true, false, true, false, true, false, false,
			true, false, false, true, true, false, true };

	private final int rows = 3;
	private final int column = 12;
	private final int totalNumbers = (rows * column);
	private ArrayList<Casella> caselles;

	/**
	 * Constructor per la grafica de la ruleta.
	 */
	public AmericanRoulette() {
		setCaselles();
	}//Tancament del constructor

	/**
	 * Setter de Caselles.
	 */
	public void setCaselles(String[] caselles) {
		this.num_casella = caselles;
	}//Tancament del setter

	/**
	 * Getter de ColorCaselles.
	 */
	public Boolean[] getColorCaselles() {
		return color_caselles;
	}//Tancament del getter

	/**
	 * Setter de ColorCaselles.
	 */
	public void setColorCaselles(Boolean[] color_caselles) {
		this.color_caselles = color_caselles;
	}//Tancament del setter

	/**
	 * Setter de Caselles.
	 */
	public void setCaselles() {
		caselles = new ArrayList<model.struct.roulette.Casella>();
		for (int i = 0; i < totalNumbers; i++) {
			caselles.add(new model.struct.roulette.Casella(Integer.parseInt(num_casella[i]), color_caselles[i]));
		}
	}//Tancament del setter
	
	/**
	 * Getter de Caselles.
	 */
	public ArrayList<Casella> getCaselles() {
		return caselles;
	}//Tancament del getter
}//Tancament de la classe
