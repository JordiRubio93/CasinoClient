/**
 * @author
 * Pol Vales - ls30599@salleurl.edu
 * Enric Marin - ls31308@salleurl.edu
 * Diego Bellino - ls30741@salleurl.edu
 * Jordi Rubio - ls31289@salleurl.edu
 * David Estepa - ls30622@salleurl.edu
 * DPO2 (Disseny i programacio orientats a objectes)
 * La Salle, Universitat Ramon Llull
 */

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
