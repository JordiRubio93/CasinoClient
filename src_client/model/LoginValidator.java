package model;

import java.util.regex.Pattern;
/**
 * 
 * <p>
 * <b> Classe: LoginValidator </b> <br/>
 * Implementa el validador del login.
 * </p>
 * 
 * @version 1.0 19/05/2016
 * @author  Pol Valés - ls30599@salleurl.edu <br/>
 * 			Diego Bellino - ls30741@salleurl.edu <br/>
 * 			Enric Marin - ls31308@salleurl.edu <br/>
 * 			Jordi Rubió - ls31289@salleurl.edu <br/>
 * 			David Estepa - ls30622@salleurl.edu <br/>
 * 			Disseny i programació orientats a objectes. <br/>
 * 			La Salle - Universitat Ramon Llull. <br/>
 * 
 */
public class LoginValidator {
	private final Pattern VALID_EMAIL_ADDRESS = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);

	private final Pattern PASSWORD_PATTERN = Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})");

	public boolean validateEmailFormat(String email) {
		return VALID_EMAIL_ADDRESS.matcher(email).find();
	}

	public boolean validatePasswordFormat(String password) {
		return PASSWORD_PATTERN.matcher(password).find();
	}
}
