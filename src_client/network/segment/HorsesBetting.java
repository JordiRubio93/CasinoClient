package network.segment;

import model.Bet;

public class HorsesBetting extends Segment {
	private static final long serialVersionUID = 1L;
	private Bet bet;

	public HorsesBetting(Bet bet) {
		this.bet = bet;
	}
	public Bet getBet() {
		return bet;
	}
	public void setBet(Bet bet) {
		this.bet = bet;
	}	
}
