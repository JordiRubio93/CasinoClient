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

import java.util.LinkedList;

import model.struct.user.HistoricPartides;

/**
 * The Class Top5.
 */
public class Top5 extends Segment {
	private static final long serialVersionUID = 1L;
	private LinkedList<HistoricPartides> historic;
	private int joc;

	/**
	 * Instantiates a new Top5 segment.
	 *
	 * @param historic
	 * @param joc
	 */
	public Top5(LinkedList<HistoricPartides> historic, int joc) {
		this.historic = historic;
		this.joc = joc;
	}

	/**
	 * Gets historic.
	 *
	 * @return historic
	 */
	public LinkedList<HistoricPartides> getHistoric() {
		return historic;
	}

	/**
	 * Sets historic.
	 *
	 * @param historic
	 */
	public void setHistoric(LinkedList<HistoricPartides> historic) {
		this.historic = historic;
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
