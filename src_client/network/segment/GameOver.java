




package network.segment;

/**
 * The Class GameOver.
 */
public class GameOver extends Segment {
	private static final long serialVersionUID = 1L;
	private int joc;

	/**
	 * Instantiates a new GameOver segment.
	 *
	 * @param joc
	 */
	public GameOver(int joc) {
		this.setJoc(joc);
	}

	/**
	 * Gets joc.
	 *
	 * @return joc
	 */
	public int getJoc() {
		return joc;
	}

	/**
	 * Sets joc.
	 *
	 * @param joc
	 */
	public void setJoc(int joc) {
		this.joc = joc;
	}
}
