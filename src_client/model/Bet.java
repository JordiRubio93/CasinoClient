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

import java.io.Serializable;

/**
 * The Class Bet.
 * (Conté les dades d'una aposta.)
 */
public class Bet implements Serializable {
	// Atributs de la classe
	private static final long serialVersionUID = 1L;
	private double amount;
	private String slot;

	/**
	 * Instantiates a new bet.
	 *
	 * @param amount
	 * @param slot
	 */
	public Bet(double amount, String slot) {
		this.amount = amount;
		this.slot = slot;
	}// Tancament del constructor

	/**
	 * Gets amount.
	 *
	 * @return amount
	 */
	public double getAmount() {
		return amount;
	}// Tancament del getter

	/**
	 * Sets amount.
	 *
	 * @param amount
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}// Tancament del setter

	/**
	 * Gets slot.
	 *
	 * @return slot
	 */
	public String getSlot() {
		return slot;
	}// Tancament del getter

	/**
	 * Sets slot.
	 *
	 * @param slot
	 */
	public void setSlot(String slot) {
		this.slot = slot;
	}// Tancament del setter

}// Tancament de la classe
