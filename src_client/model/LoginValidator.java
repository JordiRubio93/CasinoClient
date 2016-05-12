package model;

import java.util.regex.Pattern;

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
