package model;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.regex.Pattern;

import controller.Constants;

public class RegisterValidator extends LoginValidator{
	private final Pattern VALID_NAME_OR_SURNAME = Pattern.compile("[a-zA-Z]{3,20}",
			Pattern.CASE_INSENSITIVE);

	public boolean validateName(String name) {
		return VALID_NAME_OR_SURNAME.matcher(name).find() && !name.equals(Constants.guest.getName());
	}

	public Boolean validateAge(Date date) {	
		return (Period.between(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now())).getYears()>=18 ;
	}
}

