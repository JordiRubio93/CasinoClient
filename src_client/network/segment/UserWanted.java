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

import model.struct.user.User;

/**
 * The Class UserWanted.
 */
public class UserWanted extends Segment {
	private static final long serialVersionUID = 1L;
	private User user;

	/**
	 * Instantiates a new UserWanted segment.
	 *
	 * @param user
	 */
	public UserWanted(User user) {
		this.user = user;
	}

	/**
	 * Gets user.
	 *
	 * @return user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Sets user.
	 *
	 * @param user
	 */
	public void setUser(User user) {
		this.user = user;
	}
}
