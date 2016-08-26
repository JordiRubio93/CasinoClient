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

/**
 * The Class InitRoulette.
 */
public class InitRoulette extends Segment {
	private static final long serialVersionUID = 1L;
	private double guanys;
	private String winner;

	/**
	 * Instantiates a new InitRoulette segment.
	 *
	 * @param winner
	 * @param guanys
	 */
	public InitRoulette(String winner, double guanys) {
		super();
		this.winner = winner;
		this.guanys = guanys;
	}

	/**
	 * Gets guanys.
	 *
	 * @return guanys
	 */
	public double getGuanys() {
		return guanys;
	}

	/**
	 * Sets guanys.
	 *
	 * @param guanys
	 */
	public void setGuanys(double guanys) {
		this.guanys = guanys;
	}

	/**
	 * Gets winner.
	 *
	 * @return winner
	 */
	public String getWinner() {
		return winner;
	}

	/**
	 * Sets winner.
	 *
	 * @param winner
	 */
	public void setWinner(String winner) {
		this.winner = winner;
	}

}
