package network.segment;

import java.util.LinkedList;

import model.struct.user.HistoricSaldo;

public class CashEvo extends Segment {
	private static final long serialVersionUID = 1L;
	private String id;
	private int joc; //0 = Tots; 1 = Ruleta; 2 = Cavalls; 3 = BJ
	private LinkedList<HistoricSaldo> historic;
	
	public CashEvo(String id, int joc) {
		this.id = id;
		this.joc = joc;
	}

	public CashEvo(String id, int joc, LinkedList<HistoricSaldo> historic) {
		this.id = id;
		this.joc = joc;
		this.historic = historic;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getJoc() {
		return joc;
	}

	public void setJoc(int joc) {
		this.joc = joc;
	}

	public LinkedList<HistoricSaldo> getHistoric() {
		return historic;
	}

	public void setHistoric(LinkedList<HistoricSaldo> historic) {
		this.historic = historic;
	}
}
