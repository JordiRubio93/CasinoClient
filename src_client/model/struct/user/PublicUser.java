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

package model.struct.user;

import java.io.Serializable;

/**
 * The Class PublicUser.
 * (Versió pública de l'usuari.)
 */
public class PublicUser implements Serializable {
	private static final long serialVersionUID = 1L;
	private String surname;
	private boolean gender; // male true false female

	/**
	 * Instantiates a new public user.
	 *
	 * @param surname
	 * @param gender
	 */
	public PublicUser(String surname, boolean gender) {
		super();
		this.surname = surname;
		this.gender = gender;
	}

	/**
	 * Gets surname.
	 *
	 * @return surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * Sets surname.
	 *
	 * @param surname
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * Gets gender.
	 *
	 * @return gender
	 */
	public boolean getGender() {
		return gender;
	}

	/**
	 * Sets gender.
	 *
	 * @param gender
	 */
	public void setGender(boolean gender) {
		this.gender = gender;
	}
}
