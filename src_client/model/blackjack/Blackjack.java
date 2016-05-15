package model.blackjack;

public class Blackjack {
	private Deck deck;
	private Player player;
	private Dealer dealer;
	private boolean cardAce;
	
	public Blackjack(/*float cash*/) {
		deck = new Deck();
		player = new Player(500);
		dealer = new Dealer();
	}
	
	public void newGame() {
		//Barallem la baralla i tornem a posar els comptadors de player i dealer a 0
		deck.shuffleDeck();
		player.resetPlayer();
		dealer.resetDealer();
	}
	
	public int giveCard(int destination) {
		int cardValue = deck.nextCard();
		if (destination == 2){
			cardAce = false;
		}
		if (cardValue == 1 || cardValue == 14 || cardValue == 27 || cardValue == 40) {
			cardAce = true;
		}
		if (destination == 1){
			player.addValue(cardValue);
			if (player.getCardCount() > 21 && cardAce){
				player.adjustCardCount();
				cardAce = false;
			}
		}else{
			dealer.addValue(cardValue);
			if (dealer.getCardCount() > 21 && cardAce){
				dealer.adjustCardCount();
				cardAce = false;
			}
		}
		return cardValue;
	}
	
	public int getCardCount(int receiver) {
		if (receiver == 1){
			return player.getCardCount();
		}else{
			return dealer.getCardCount();
		}
	}
	
	public float getCashAmount() {
		return player.getCash();
	}
	
	public void setCashAmount(int cash) {
		player.setCash(cash);
	}
	
	public void addBet(float bet) {
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
