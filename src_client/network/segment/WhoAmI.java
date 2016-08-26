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
 * The Class WhoAmI.
 */
public class WhoAmI extends Segment {
	private static final long serialVersionUID = 1L;
	private String id;

	/**
	 * Instantiates a new WhoAmI segment.
	 *
	 * @param id
	 */
	public WhoAmI(String id) {
		this.setId(id);
	}

	/**
	 * Gets id.
	 *
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets id.
	 *
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}
}
