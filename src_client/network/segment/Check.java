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
 * The Class Check.
 */
public class Check extends Segment {
	private static final long serialVersionUID = 1L;
	private boolean ok;

	/**
	 * Instantiates a new Check segment.
	 *
	 * @param b
	 */
	public Check(boolean b) {
		this.ok = b;
	}

	/**
	 * Checks if is ok.
	 *
	 * @return true, if is ok
	 */
	public boolean isOk() {
		return ok;
	}
}
