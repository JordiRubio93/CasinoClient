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

import model.Bet;

/**
 * The Class Player.
 * (Part lògica referent al jugador del blackjack i la seva mà.)
 */
public class Player {
	private double cash, initialCash;
	private int cardCount;
	private Bet bet;

	/**
	 * Instantiates a new player.
	 *
	 * @param cash
	 */
	public Player(double cash) {
		this.cardCount = 0;
		this.cash = cash;
		this.initialCash = cash;
		bet = new Bet(0, "blackjack");
	}

	/**
	 * Adds value.
	 *
	 * @param nCard
	 */
	public void addValue(int nCard) {
		int cardValue;
		boolean ok;
		if (cardCount < 21) {
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
	 * @return card count
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
	 * Gets cash.
	 *
	 * @return cash
	 */
	public double getCash() {
		return cash;
	}

	/**
	 * Sets cash.
	 *
	 * @param cash
	 */
	public void setCash(double cash) {
		this.cash = cash;
	}

	/**
	 * Reset player.
	 */
	public void resetPlayer() {
		this.cardCount = 0;
	}

	/**
	 * Sets bet.
	 *
	 * @param bet
	 */
	public void setBet(double bet) {
		this.bet = new Bet(bet, "blackjack");
	}

	/**
	 * Gets bet.
	 *
	 * @return bet
	 */
	public double getBet() {
		return bet.getAmount();
	}

	/**
	 * Gets initial cash.
	 *
	 * @return initial cash
	 */
	public double getInitialCash() {
		return initialCash;
	}
}