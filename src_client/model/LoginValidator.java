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

package model;

import java.util.regex.Pattern;

/**
 * The Class LoginValidator.
 * (Valida formats a través de patrons.)
 */
public class LoginValidator {
	private final Pattern VALID_EMAIL_ADDRESS = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);

	private final Pattern PASSWORD_PATTERN = Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})");

	/**
	 * Validate email format.
	 *
	 * @param email
	 * @return true, if successful
	 */
	public boolean validateEmailFormat(String email) {
		return VALID_EMAIL_ADDRESS.matcher(email).find();
	}

	/**
	 * Validate password format.
	 *
	 * @param password
	 * @return true, if successful
	 */
	public boolean validatePasswordFormat(String password) {
		return PASSWORD_PATTERN.matcher(password).find();
	}
}
