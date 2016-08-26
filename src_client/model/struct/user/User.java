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
import java.util.Date;

/**
 * The Class User.
 * (Conté les dades de l'usuari.)
 */
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private String surname;
	private LoginInfo loginInfo;
	private double cash;
	private Date register;
	private Date lastLogin;
	private Date birthday;
	private boolean gender; // male true false female

	/**
	 * Instantiates a new user.
	 *
	 * @param name
	 * @param surname
	 * @param password
	 * @param cash
	 * @param email
	 * @param register
	 * @param lastLogin
	 * @param birthday
	 * @param gender
	 */
	public User(String name, String surname, String password, double cash, String email, Date register, Date lastLogin,
			Date birthday, boolean gender) {
		super();
		this.name = name;
		this.surname = surname;
		this.loginInfo = new LoginInfo(email, password);
		this.cash = cash;
		this.register = register;
		this.lastLogin = lastLogin;
		this.birthday = birthday;
		this.gender = gender;
	}

	/**
	 * Instantiates a new user.
	 *
	 * @param email
	 * @param password
	 */
	public User(String email, String password) {
		this.loginInfo = new LoginInfo(email, password);
	}

	/**
	 * Gets name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets name.
	 *
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets surname.
	 *
	 * @return the surname
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
	 * Gets email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return this.loginInfo.getEmail();
	}

	/**
	 * Gets password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return this.loginInfo.getPassword();
	}

	/**
	 * Gets gender.
	 *
	 * @return the gender
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

	/**
	 * Gets register date.
	 *
	 * @return the register date
	 */
	public Date getRegister() {
		return register;
	}

	/**
	 * Sets register date.
	 *
	 * @param register date
	 */
	public void setRegister(Date register) {
		this.register = register;
	}

	/**
	 * Gets birthday.
	 *
	 * @return the birthday
	 */
	public Date getBirthday() {
		return birthday;
	}

	/**
	 * Sets birthday.
	 *
	 * @param birthday
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	/**
	 * Gets cash.
	 *
	 * @return the cash
	 */
	public double getCash() {
		return cash;
	}

	/**
	 * Sets cash.
	 *
	 * @param cash
	 */
	public void setCash(double cash) {
		this.cash = cash;
	}

	/**
	 * Gets public user.
	 *
	 * @return the public user
	 */
	public PublicUser getPublicUser() {
		return new PublicUser(getSurname(), getGender());
	}

	/**
	 * Gets last login date.
	 *
	 * @return the last login date
	 */
	public Date getLastLogin() {
		return lastLogin;
	}

	/**
	 * Sets last login date.
	 *
	 * @param lastLogin date
	 */
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	/**
	 * Change password.
	 *
	 * @param pw
	 */
	public void changePassword(String pw) {
		this.loginInfo.setPassword(pw);
	}

	/**
	 * Change email.
	 *
	 * @param email
	 */
	public void changeEmail(String email) {
		this.loginInfo.setEmail(email);
	}

	/**
	 * Encrypt password.
	 */
	public void encryptPassword() {
		loginInfo.encryptPassword();
	}

	/**
	 * Gets login info.
	 *
	 * @return the login info
	 */
	public LoginInfo getLoginInfo() {
		return loginInfo;
	}

	/**
	 * Sets login info.
	 *
	 * @param loginInfo
	 */
	public void setLoginInfo(LoginInfo loginInfo) {
		this.loginInfo = loginInfo;
	}

}