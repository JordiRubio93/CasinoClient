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
