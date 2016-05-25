package model.blackjack;

import java.util.Arrays;
import java.util.Collections;

/**
 * 
 * <p>
 * <b> Classe: Deck </b> <br/>
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

public class Deck {
	private Integer[] deck;
	private int cardPointer;
	
	/**
	 * Constructor de la classe deck, on es crea un array amb les 52 cartes.
	 */
	
	public Deck() {
		deck = new Integer[52];
		for (int i = 0; i < deck.length; i++) {
			deck[i] = i + 1;
		}	
		cardPointer = 0;
	}
	
	/**
	 * Funcio que s'encarrega de barallar la baralla aleatoriament.
	 */
	
	public void shuffleDeck() {
		Collections.shuffle(Arrays.asList(deck));
		cardPointer = 0;
	} 
	
	/**
	 * Funcio que s'encarrega de treure la seguent carta de la baralla.
	 * @return Retorna el numero identificatiu de la seguent carta.
	 */
	
	public int nextCard() {
		int cardNumber = deck[cardPointer];
		cardPointer ++;
		return cardNumber;
	}
}
