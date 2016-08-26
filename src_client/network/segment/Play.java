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
 * The Class Play.
 */
public class Play extends Segment {
	private static final long serialVersionUID = 1L;
	private String tipus;

	/**
	 * Instantiates a new Play segment.
	 *
	 * @param tipus
	 */
	public Play(String tipus) {
		this.tipus = tipus;
	}

	/**
	 * Gets tipus.
	 *
	 * @return tipus
	 */
	public String getTipus() {
		return tipus;
	}
}
