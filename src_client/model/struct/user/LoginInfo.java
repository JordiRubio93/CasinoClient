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
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * The Class LoginInfo.
 * (Conté la informació d'inici de l'usuari.)
 */
public class LoginInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String email;
	private String password;
	private Boolean pwhash; // ho nesecitem per el recuerdame!

	/**
	 * Instantiates a new login info.
	 *
	 * @param email
	 * @param password
	 */
	public LoginInfo(String email, String password) {
		super();
		this.email = email;
		this.password = password;
		this.pwhash = Boolean.FALSE;
	}

	/**
	 * Instantiates a new login info.
	 *
	 * @param email
	 * @param password
	 * @param pwhash
	 */
	public LoginInfo(String email, String password, Boolean pwhash) {
		super();
		this.email = email;
		this.password = password;
		this.pwhash = pwhash;
	}

	/**
	 * Gets email.
	 *
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets email.
	 *
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Sets password.
	 *
	 * @param pw
	 */
	public void setPassword(String pw) {
		this.password = pw;
		this.pwhash = Boolean.FALSE;
	}

	/**
	 * Gets password.
	 *
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * (Encripta la contrasenya.)
	 */
	public void encryptPassword() {
		if (!pwhash) {
			try {
				this.password = toSHA1(getPassword().getBytes("UTF-8"));
				pwishashed();
			} catch (UnsupportedEncodingException e) {
				// manager.showMessage(e.getMessage());
			}
		}
	}

	/**
	 * Posa el booleà de que està amb hash a cert.
	 */
	public void pwishashed() {
		this.pwhash = Boolean.TRUE;
	}

	/**
	 * Gets pwhash.
	 *
	 * @return pwhash
	 */
	public Boolean getPwhash() {
		return pwhash;
	}

	/**
	 * (Crea el hash de la contrasenya amb SHA1.)
	 *
	 * @param convertme
	 * @return string
	 */
	// TODO MOVE
	public String toSHA1(byte[] convertme) {
		// MessageDigest md = null;
		String digestStr = null;
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			digest.update(convertme);
			byte[] digestBytes = digest.digest();
			digestStr = javax.xml.bind.DatatypeConverter.printHexBinary(digestBytes);
			// md = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {

		}
		return digestStr.toLowerCase();
	}

	/**
	 * (Passa tota la classe a cadena de caràcters.)
	 */
	public String toString() {
		return email + "@@" + password + "@@" + pwhash.toString();
	}
}
