




package network.segment;

import java.util.LinkedList;

import model.struct.user.HistoricSaldo;

/**
 * The Class CashEvo.
 */
public class CashEvo extends Segment {
	private static final long serialVersionUID = 1L;
	private String id;
	private int joc; // 0 = Tots; 1 = Ruleta; 2 = Cavalls; 3 = BJ
	private LinkedList<HistoricSaldo> historic;

	/**
	 * Instantiates a new CashEvo segment.
	 *
	 * @param id
	 * @param joc
	 */
	public CashEvo(String id, int joc) {
		this.id = id;
		this.joc = joc;
	}

	/**
	 * Instantiates a new cash evo.
	 *
	 * @param id
	 * @param joc
	 * @param historic
	 */
	public CashEvo(String id, int joc, LinkedList<HistoricSaldo> historic) {
		this.id = id;
		this.joc = joc;
		this.historic = historic;
	}

	/**
	 * Gets id.
	 *
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets id.
	 *
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
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

	/**
	 * Gets historic.
	 *
	 * @return historic
	 */
	public LinkedList<HistoricSaldo> getHistoric() {
		return historic;
	}

	/**
	 * Sets historic.
	 *
	 * @param historic
	 */
	public void setHistoric(LinkedList<HistoricSaldo> historic) {
		this.historic = historic;
	}
}
