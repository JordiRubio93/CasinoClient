package model;

import java.io.Serializable;

/**
 * 
 * <p>
 * <b> Classe: Bet </b> <br/>
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
public class Bet implements Serializable {
	//Atributs de la classe
    private static final long serialVersionUID = 1L;
	private double amount;
	private String slot;
	
	/**
	 * Constructor per la classe bet.
	 */
	public Bet(double amount, String slot) {
		this.amount = amount;
		this.slot = slot;
	}//Tancament del constructor
	
	/**
	 * Getter de Amount.
	 */
	public double getAmount() {
		return amount;
	}//Tancament del getter
	
	/**
	 * Setter de Amount.
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}//Tancament del setter
	
	/**
	 * Getter de Slot.
	 */
	public String getSlot() {
		return slot;
	}//Tancament del getter
	
	/**
	 * Setter de Slot.
	 */
	public void setSlot(String slot) {
		this.slot = slot;
	}//Tancament del setter
	
}//Tancament de la classe

