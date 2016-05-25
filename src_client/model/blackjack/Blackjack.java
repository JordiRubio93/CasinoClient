package model.blackjack;


import controller.Constants;
import model.struct.user.User;

/**
 * 
 * <p>
 * <b> Classe: Blackjack </b> <br/>
 * Implementa la logica del blackjack.
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
public class Blackjack {
	private Deck deck;
	private Player player;
	private Dealer dealer;
	private int cardAce;
	private boolean okBet;

	public Blackjack(User u) {
		okBet = false;
		deck = new Deck();
		// TODO usuario real
		player = new Player(u.getCash());
		dealer = new Dealer();
	}
	
	/**
	 * Indica si l'usuari pot o no apostar.
	 * @param bet, objecte de la clase Bet
	 * @return boole� indicant si pot o no apostar
	 */
	public boolean canBet(double bet) {
		return (bet >= Constants.MIN_BET && bet <= getCashAmount());
	}
	
	/**
	 * Prepara un joc nou
	 */
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
			cardAce = 0;
		}
		if (cardValue == 1 || cardValue == 14 || cardValue == 27 || cardValue == 40) {
			cardAce = cardAce+1;
		}
		if (destination == 1) {
			player.addValue(cardValue);
			if (player.getCardCount() > 21 && cardAce != 0) {
				player.adjustCardCount();
				cardAce = cardAce-1;
			}
		} else {
			dealer.addValue(cardValue);
			if (dealer.getCardCount() > 21 && cardAce != 0) {
				dealer.adjustCardCount();
				cardAce = cardAce-1 ;
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
	
	/**
	 * Es para la partida
	 * @param win, indica si l'usuari ha guanyat o no
	 */
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
