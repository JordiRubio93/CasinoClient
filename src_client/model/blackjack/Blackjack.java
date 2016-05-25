package model.blackjack;


import controller.Constants;
import model.struct.user.User;

/**
 * 
 * <p>
 * <b> Classe: Blackjack </b> <br/>
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
	
	/**
	 * Constructor del model del blackjack
	 * @param u Serveix per determinar els diners del player.
	 */

	public Blackjack(User u) {
		okBet = false;
		deck = new Deck();
		// TODO usuario real
		player = new Player(u.getCash());
		dealer = new Dealer();
	}
	
	/**
	 * Funcio que mira que l'aposta feta pel player sigui valida.
	 * @param bet Cantitat apostada pel player.
	 * @return Retorna un boolea que indica si l'aposta es correcta o no.
	 */
	
	public boolean canBet(double bet) {
		return (bet >= Constants.MIN_BET && bet <= getCashAmount());
	}
	
	/**
	 * Metode que s'encarrega de barallar la baralla i posar els comptadors a 0 per començar un nou joc.
	 */
	
	public void newGame() {
		// Barallem la baralla i tornem a posar els comptadors de player i
		// dealer a 0
		deck.shuffleDeck();
		player.resetPlayer();
		dealer.resetDealer();
	}
	
	/**
	 * Funcio encarregada de donar una carta a player o dealer.
	 * @param destination A qui va dirigida la carta (player(1) o dealer(2)).
	 * @return Retorna l'identificador de la carta.
	 */


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
	
	/**
	 * Funcio que determina si el player/dealer poden demanar mes cartes.
	 * @param player Destinacio de qui vols determinar si pot rebre mes cartes.
	 * @param more Boolea que determina si pots demanar mes cartes o no.
	 * @param num Numero maxim de comptador del player.
	 * @return Boolea que determina si pot demanar mes cartes o no.
	 */
	
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
	 * Funcio que determina si sumar o restar la cantitat de diners apostada.
	 * @param win Boolean que determina si el player ha guanyat o ha perdut.
	 */
	
	public void stand(boolean win) {
		if (win)
			playerWins();
		else
			playerLoses();
	}
	
	/**
	 * Funcio que s'encarrega de retornar el comptador de cartes.
	 * @param receiver De qui vols el compte.
	 * @return Valor del comptador de cartes.
	 */

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
	
	/**
	 * Afegeix la aposta feta pel player.
	 * @param bet Cantitat de diners que ha apostat el player.
	 */

	public void addBet(double bet) {
		player.setBet(bet);
	}
	
	/**
	 * Resta la cantitat de diners apostada en cas que el player perdi.
	 */

	public void playerLoses() {
		player.setCash(player.getCash() - player.getBet());
	}
	
	/**
	 * Suma la cantitat de diners apostada en cas que el player guanyi.
	 */

	public void playerWins() {
		player.setCash(player.getCash() + player.getBet());
	}
	
	/**
	 * Suma la cantitat de diners multiplicada per 1.5 en cas que el player tregui blackjack.
	 */

	public void playerBlackjack() {
		player.setCash(player.getCash() + (3 * player.getBet()) / 2);
	}
}
