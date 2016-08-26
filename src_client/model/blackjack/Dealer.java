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


/**
 * The Class Dealer.
 * (Part lògica referent al repartidor de cartes del blackjack.)
 */
public class Dealer {
	private int cardCount;

	/**
	 * Instantiates a new dealer.
	 */
	public Dealer() {
		cardCount = 0;
	}

	/**
	 * Adds value.
	 *
	 * @param nCard
	 */
	public void addValue(int nCard) {
		int cardValue;
		boolean ok;
		if (cardCount < 17) {
			ok = true;
		} else {
			ok = false;
		}
		if (ok) {
			cardValue = nCard - ((nCard / 13) * 13);
			if (cardValue <= 10 && cardValue > 1) {
				cardCount = cardCount + cardValue;
			} else if (cardValue == 1) {
				cardCount = cardCount + 11;
			} else {
				cardCount = cardCount + 10;
			}
		}
	}

	/**
	 * Gets card count.
	 *
	 * @return the card count
	 */
	public int getCardCount() {
		return cardCount;
	}

	/**
	 * Adjust card count.
	 */
	public void adjustCardCount() {
		cardCount = cardCount - 10;
	}

	/**
	 * Reset dealer.
	 */
	public void resetDealer() {
		cardCount = 0;
	}
}