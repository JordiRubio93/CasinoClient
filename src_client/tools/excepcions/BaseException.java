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

package tools.excepcions;

/**
 * The Class BaseException.
 */
public class BaseException extends Exception {
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new base exception.
	 *
	 * @param msg
	 */
	public BaseException(String msg) {
		super(msg);
	}

}