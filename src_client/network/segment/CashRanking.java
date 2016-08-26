




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
