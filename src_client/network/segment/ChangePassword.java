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
 * The Class ChangePassword.
 */
public class ChangePassword extends Segment {
	private static final long serialVersionUID = 1L;
	private String newPassword;

	/**
	 * Instantiates a new ChangePassword segment.
	 *
	 * @param new password
	 */
	public ChangePassword(String newPassword) {
		super();
		this.newPassword = newPassword;
	}

	/**
	 * Gets new password.
	 *
	 * @return new password
	 */
	public String getNewPassword() {
		return newPassword;
	}

	/**
	 * Sets new password.
	 *
	 * @param new password
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
}
