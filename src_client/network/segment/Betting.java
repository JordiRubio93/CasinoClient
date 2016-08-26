




package network.segment;

import model.Bet;

/**
 * The Class Betting.
 */
public class Betting extends Segment {
	private static final long serialVersionUID = 1L;
	private Bet bet;
	
	/**
	 * Instantiates a new Betting segment.
	 *
	 * @param bet
	 */
	public Betting(Bet bet) {
		this.bet = bet;
	}
		
	/**
	 * Gets bet.
	 *
	 * @return bet
	 */
	public Bet getBet() {
		return bet;
	}
	
	/**
	 * Sets bet.
	 *
	 * @param bet
	 */
	public void setBet(Bet bet) {
		this.bet = bet;
	}	
}
