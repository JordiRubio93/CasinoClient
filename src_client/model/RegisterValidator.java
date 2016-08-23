package model;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.regex.Pattern;

import controller.Constants;
/**
 * 
 * <p>
 * <b> Classe: RegisterValidator </b> <br/>
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
public class RegisterValidator extends LoginValidator{
	private final Pattern VALID_NAME_OR_SURNAME = Pattern.compile("[a-zA-Z]{3,20}",
			Pattern.CASE_INSENSITIVE);
	private final Pattern HAVE_NUMBER = Pattern.compile(".*\\d.*",
			Pattern.CASE_INSENSITIVE);

	public boolean validateName(String name) {
		return VALID_NAME_OR_SURNAME.matcher(name).find() && !name.equals(Constants.guest.getName()) && !HAVE_NUMBER.matcher(name).find();
	}

	public Boolean validateAge(Date date) {	
		System.out.println(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		return (Period.between(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now())).getYears()>=18 ;
	}
}

