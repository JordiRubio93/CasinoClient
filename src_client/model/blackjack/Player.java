package model.blackjack;

import model.Bet;

/**
 * 
 * <p>
 * <b> Classe: Player </b> <br/>
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

public class Player {
	private double cash, initialCash;
	private int cardCount;
	private Bet bet;
	
	/**
	 * Constructor de la classe player del model.
	 * @param cash
	 */
	
	public Player(double cash) {
		this.cardCount = 0;
		this.cash = cash;
		this.initialCash = cash;
		bet = new Bet(0, "blackjack");
	}
	
	/**
	 * Funcio que afegeix el valor de la carta que ha obtingut el player al seu comptador.
	 * @param nCard Numero identificador de la carta.
	 */
	
	public void addValue(int nCard) {
		int cardValue;
		boolean ok;
		if (cardCount < 21){
			ok = true;
		}else{
			ok= false;
		}
		if (ok){
			cardValue = nCard - ((nCard/13)*13);
			if (cardValue <= 10 && cardValue > 1){
				cardCount = cardCount + cardValue;
			}else if (cardValue == 1){
				cardCount = cardCount + 11;
			}else{
				cardCount = cardCount + 10;
			}
		}
	}
	
	public int getCardCount() {
		return cardCount;
	}
	
	/**
	 * Funcio que s'encarrega d'ajustar el comptador del player encas que aquest tingui un as i es passi de 21.
	 */
	
	public void adjustCardCount() {
		cardCount = cardCount - 10;
	}
	
	public double getCash() {
		return cash;
	}
	
	public void setCash(double cash) {
		this.cash = cash;
	}
	
	/**
	 * Funcio que s'encarrega se resetejar el comptador per començar una nova partida.
	 */
	
	public void resetPlayer() {
		this.cardCount = 0;
	}
	
	public void setBet(double bet) {
		this.bet = new Bet(bet, "blackjack");
	}
	
	public double getBet() {
		return bet.getAmount();
	}
	
	public double getInitialCash() {
		return initialCash;
	}
}