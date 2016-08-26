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

package model.blackjack;

import java.util.Arrays;
import java.util.Collections;

/**
 * The Class Deck.
 * (Part lògica referent a la baralla de cartes del blackjack.)
 */
public class Deck {
	private Integer[] deck;
	private int cardPointer;

	/**
	 * Instantiates a new deck.
	 */
	public Deck() {
		deck = new Integer[52];
		for (int i = 0; i < deck.length; i++) {
			deck[i] = i + 1;
		}
		cardPointer = 0;
	}

	/**
	 * Shuffle deck.
	 */
	public void shuffleDeck() {
		Collections.shuffle(Arrays.asList(deck));
		cardPointer = 0;
	}

	/**
	 * Next card.
	 *
	 * @return int
	 */
	public int nextCard() {
		int cardNumber = deck[cardPointer];
		cardPointer++;
		return cardNumber;
	}
}
