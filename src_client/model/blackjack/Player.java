package model.blackjack;

import model.struct.bet.Bet;

public class Player {
	private float cash, initialCash;
	private int cardCount;
	private Bet bet;
	
	public Player(int cash) {
		this.cardCount = 0;
		this.cash = cash;
		this.initialCash = cash;
		bet = new Bet(0);
	}
	
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
	
	public void adjustCardCount() {
		cardCount = cardCount - 10;
	}
	
	public float getCash() {
		return cash;
	}
	
	public void setCash(float cash) {
		this.cash = cash;
	}
	
	public void resetPlayer() {
		this.cardCount = 0;
	}
	
	public void setBet(float bet) {
		this.bet = new Bet(bet);
	}
	
	public float getBet() {
		return bet.getAmount();
	}
	
	public float getInitialCash() {
		return initialCash;
	}
}
