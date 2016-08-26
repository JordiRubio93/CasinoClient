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
 * The Class AddCash.
 */
public class AddCash extends Segment {
	private static final long serialVersionUID = 1L;
	private float cash;
	private String password;

	/**
	 * Instantiates a new AddCash segment.
	 *
	 * @param cash
	 * @param password
	 */
	public AddCash(float cash, String password) {
		super();
		this.cash = cash;
		this.password = password;
	}

	/**
	 * Gets cash.
	 *
	 * @return cash
	 */
	public float getCash() {
		return cash;
	}

	/**
	 * Sets cash.
	 *
	 * @param cash
	 */
	public void setCash(float cash) {
		this.cash = cash;
	}

	/**
	 * Gets password.
	 *
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets password.
	 *
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
