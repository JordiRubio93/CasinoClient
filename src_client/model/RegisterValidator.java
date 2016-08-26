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

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.regex.Pattern;

import controller.Constants;

/**
 * The Class RegisterValidator.
 * (Valida formats a través de patrons.)
 */
public class RegisterValidator extends LoginValidator {
	private final Pattern VALID_NAME_OR_SURNAME = Pattern.compile("[a-zA-Z]{3,20}", Pattern.CASE_INSENSITIVE);
	private final Pattern HAVE_NUMBER = Pattern.compile(".*\\d.*", Pattern.CASE_INSENSITIVE);

	/**
	 * Validate name.
	 *
	 * @param name
	 * @return true, if successful
	 */
	public boolean validateName(String name) {
		return VALID_NAME_OR_SURNAME.matcher(name).find() && !name.equals(Constants.guest.getName())
				&& !HAVE_NUMBER.matcher(name).find();
	}

	/**
	 * Validate birthday.
	 *
	 * @param date
	 * @return boolean
	 */
	public Boolean validateAge(Date date) {
		// System.out.println(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		return (Period.between(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now()))
				.getYears() >= 18;
	}
}
