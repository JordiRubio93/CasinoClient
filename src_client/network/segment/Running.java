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
 * The Class Running.
 */
public class Running extends Segment {
	private static final long serialVersionUID = 1L;
	private boolean running;
	private int joc;

	/**
	 * Instantiates a new running.
	 *
	 * @param running
	 * @param joc
	 */
	public Running(boolean running, int joc) {
		this.running = running;
		this.joc = joc;
	}

	/**
	 * Checks if is running.
	 *
	 * @return true, if is running
	 */
	public boolean isRunning() {
		return running;
	}

	/**
	 * Sets running.
	 *
	 * @param running
	 */
	public void setRunning(boolean running) {
		this.running = running;
	}

	/**
	 * Gets joc.
	 *
	 * @return joc
	 */
	public int getJoc() {
		return joc;
	}

	/**
	 * Sets joc.
	 *
	 * @param joc
	 */
	public void setJoc(int joc) {
		this.joc = joc;
	}
}
