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
 * The Class Seconds.
 */
public class Seconds extends Segment {
	private static final long serialVersionUID = 1L;
	private int segons;

	/**
	 * Instantiates a new Seconds segment.
	 *
	 * @param segons
	 */
	public Seconds(int segons) {
		this.setSegons(segons);
	}

	/**
	 * Gets segons.
	 *
	 * @return segons
	 */
	public int getSegons() {
		return segons;
	}

	/**
	 * Sets segons.
	 *
	 * @param segons
	 */
	public void setSegons(int segons) {
		this.segons = segons;
	}
}
