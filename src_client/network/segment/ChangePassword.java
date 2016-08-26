




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
