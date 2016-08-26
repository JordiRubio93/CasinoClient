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

package network.segment;

/**
 * The Class Disconnect.
 */
public class Disconnect extends Segment {
	private static final long serialVersionUID = 1L;
	private boolean led;

	/**
	 * Instantiates a new Disconnect segment.
	 *
	 * @param led
	 */
	public Disconnect(boolean led) {
		this.led = led;
	}

	/**
	 * Checks if is led.
	 *
	 * @return true, if is led
	 */
	public boolean isLed() {
		return led;
	}
}
