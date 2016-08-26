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

import model.struct.user.LoginInfo;

/**
 * The Class LoginUser.
 */
public class LoginUser extends Segment {
	private static final long serialVersionUID = 1L;
	private LoginInfo u;

	/**
	 * Instantiates a new LoginUser segment.
	 *
	 * @param u
	 */
	public LoginUser(LoginInfo u) {
		this.u = u;
	}

	/**
	 * Gets u.
	 *
	 * @return u
	 */
	public LoginInfo getU() {
		return u;
	}

	/**
	 * Sets u.
	 *
	 * @param u
	 */
	public void setU(LoginInfo u) {
		this.u = u;
	}
}
