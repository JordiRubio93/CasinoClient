package model.blackjack;

public class Dealer {
	private int cardCount;
	
	public Dealer() {
		cardCount = 0;
	}

	public void addValue(int nCard) {
		int cardValue;
		boolean ok;
		if (cardCount < 17){
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
	
	public void resetDealer() {
		cardCount = 0;
	}
}