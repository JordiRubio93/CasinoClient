




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
