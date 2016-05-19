package model.blackjack;

import controller.Constants;
import model.struct.user.User;

public class Blackjack {
	private Deck deck;
	private Player player;
	private Dealer dealer;
	private boolean cardAce;
	private boolean okBet;

	public Blackjack(User u) {
		okBet = false;
		deck = new Deck();
		// TODO usuario real
		player = new Player(u.getCash());
		dealer = new Dealer();
	}

	public boolean canBet(double bet) {
		return (bet >= Constants.MIN_BET && bet <= getCashAmount());
	}

	public void newGame() {
		// Barallem la baralla i tornem a posar els comptadors de player i
		// dealer a 0
		deck.shuffleDeck();
		player.resetPlayer();
		dealer.resetDealer();
	}

	public int giveCard(int destination) {
		int cardValue = deck.nextCard();
		if (destination == 2) {
			cardAce = false;
		}
		if (cardValue == 1 || cardValue == 14 || cardValue == 27 || cardValue == 40) {
			cardAce = true;
		}
		if (destination == 1) {
			player.addValue(cardValue);
			if (player.getCardCount() > 21 && cardAce) {
				player.adjustCardCount();
				cardAce = false;
			}
		} else {
			dealer.addValue(cardValue);
			if (dealer.getCardCount() > 21 && cardAce) {
				dealer.adjustCardCount();
				cardAce = false;
			}
		}
		return cardValue;
	}

	public boolean getCount(int player, boolean more, int num) {
		if (more)
			return ((getCardCount(player) > num));
		return ((getCardCount(player) < num));
	}

	public boolean isOkBet() {
		return okBet;
	}

	public void setOkBet(boolean okBet) {
		this.okBet = okBet;
	}

	public void stand(boolean win) {
		if (win)
			playerWins();
		else
			playerLoses();
	}

	public int getCardCount(int receiver) {
		if (receiver == 1) {
			return player.getCardCount();
		} else {
			return dealer.getCardCount();
		}
	}

	public double getCashAmount() {
		return player.getCash();
	}

	public void setCashAmount(int cash) {
		player.setCash(cash);
	}

	public void addBet(double bet) {
		player.setBet(bet);
	}

	public void playerLoses() {
		player.setCash(player.getCash() - player.getBet());
	}

	public void playerWins() {
		player.setCash(player.getCash() + player.getBet());
	}

	public void playerBlackjack() {
		player.setCash(player.getCash() + (3 * player.getBet()) / 2);
	}
}
