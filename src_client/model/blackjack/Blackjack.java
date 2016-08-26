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

import model.struct.user.User;
import controller.Constants;

/**
 * The Class Blackjack.
 * (Part lògica referent a la partida del blackjack.)
 */
public class Blackjack {
	private Deck deck;
	private Player player;
	private Dealer dealer;
	private int cardAce;
	private boolean okBet;

	/**
	 * Instantiates a new blackjack.
	 *
	 * @param u
	 */
	public Blackjack(User u) {
		okBet = false;
		deck = new Deck();
		// TODO usuario real
		player = new Player(u.getCash());
		dealer = new Dealer();
	}

	/**
	 * (Diu si pot apostar.)
	 *
	 * @param bet
	 * @return true, if successful
	 */
	public boolean canBet(double bet) {
		return (bet >= Constants.MIN_BET && bet <= getCashAmount());
	}

	/**
	 * (Inicia una nova partida.)
	 */
	public void newGame() {
		// Barallem la baralla i tornem a posar els comptadors de player i
		// dealer a 0
		deck.shuffleDeck();
		player.resetPlayer();
		dealer.resetDealer();
	}

	/**
	 * (Entrega una carta.)
	 *
	 * @param destination
	 * @return int
	 */
	public int giveCard(int destination) {
		int cardValue = deck.nextCard();
		if (destination == 2) {
			cardAce = 0;
		}
		if (cardValue == 1 || cardValue == 14 || cardValue == 27 || cardValue == 40) {
			cardAce = cardAce + 1;
		}
		if (destination == 1) {
			player.addValue(cardValue);
			if (player.getCardCount() > 21 && cardAce != 0) {
				player.adjustCardCount();
				cardAce = cardAce - 1;
			}
		} else {
			dealer.addValue(cardValue);
			if (dealer.getCardCount() > 21 && cardAce != 0) {
				dealer.adjustCardCount();
				cardAce = cardAce - 1;
			}
		}
		return cardValue;
	}

	/**
	 * (Fa un recompte de cartes.)
	 *
	 * @param player
	 * @param more
	 * @param num
	 * @return the count
	 */
	public boolean getCount(int player, boolean more, int num) {
		if (more)
			return ((getCardCount(player) > num));
		return ((getCardCount(player) < num));
	}

	/**
	 * Checks if is ok bet.
	 *
	 * @return true, if is ok bet
	 */
	public boolean isOkBet() {
		return okBet;
	}

	/**
	 * Sets ok bet.
	 *
	 * @param okBet
	 */
	public void setOkBet(boolean okBet) {
		this.okBet = okBet;
	}

	/**
	 * (Quan el jugador es planta, anuncia la victòria o la derrota.)
	 *
	 * @param win
	 */
	public void stand(boolean win) {
		if (win)
			playerWins();
		else
			playerLoses();
	}

	/**
	 * Gets card count.
	 *
	 * @param receiver
	 * @return card count
	 */
	public int getCardCount(int receiver) {
		if (receiver == 1) {
			return player.getCardCount();
		} else {
			return dealer.getCardCount();
		}
	}

	/**
	 * Gets cash amount.
	 *
	 * @return cash amount
	 */
	public double getCashAmount() {
		return player.getCash();
	}

	/**
	 * Sets cash amount.
	 *
	 * @param cash
	 */
	public void setCashAmount(int cash) {
		player.setCash(cash);
	}

	/**
	 * Adds bet.
	 *
	 * @param bet
	 */
	public void addBet(double bet) {
		player.setBet(bet);
	}

	/**
	 * Player loses.
	 */
	public void playerLoses() {
		player.setCash(player.getCash() - player.getBet());
	}

	/**
	 * Player wins.
	 */
	public void playerWins() {
		player.setCash(player.getCash() + player.getBet());
	}

	/**
	 * Player blackjack.
	 */
	public void playerBlackjack() {
		player.setCash(player.getCash() + (3 * player.getBet()) / 2);
	}
}
