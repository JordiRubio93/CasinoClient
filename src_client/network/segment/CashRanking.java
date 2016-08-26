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

import java.util.ArrayList;

/**
 * The Class CashRanking.
 */
public class CashRanking extends Segment {
	private static final long serialVersionUID = 1L;

	private ArrayList<Object[]> data;

	/**
	 * Instantiates a new CashRanking segment.
	 *
	 * @param data
	 */
	public CashRanking(ArrayList<Object[]> data) {
		this.data = data;
	}

	/**
	 * Gets data.
	 *
	 * @return data
	 */
	public ArrayList<Object[]> getData() {
		return data;
	}
}
