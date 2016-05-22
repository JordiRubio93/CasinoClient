package network.segment;

import model.Bet;

public class Betting extends Segment {
	private static final long serialVersionUID = 1L;
	private Bet bet;

	public Betting(Bet bet) {
		this.bet = bet;
	}
	public Bet getBet() {
		return bet;
	}
	public void setBet(Bet bet) {
		this.bet = bet;
	}	
}
