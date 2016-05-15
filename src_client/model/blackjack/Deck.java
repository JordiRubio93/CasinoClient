package model.blackjack;

import java.util.Arrays;
import java.util.Collections;

public class Deck {
	private Integer[] deck;
	private int cardPointer;
	
	public Deck() {
		deck = new Integer[52];
		for (int i = 0; i < deck.length; i++) {
			deck[i] = i + 1;
		}	
		cardPointer = 0;
	}
	
	public void shuffleDeck() {
		Collections.shuffle(Arrays.asList(deck));
		cardPointer = 0;
	} 
	
	public int nextCard() {
		int cardNumber = deck[cardPointer];
		cardPointer ++;
		return cardNumber;
	}
}
