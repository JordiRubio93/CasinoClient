package network.segment;

import java.util.LinkedList;

import model.struct.user.HistoricPartides;

public class Top5 extends Segment{
	private static final long serialVersionUID = 1L;
	private LinkedList<HistoricPartides> historic;
	private int joc;
	
	public Top5(LinkedList<HistoricPartides> historic, int joc) {
		this.historic = historic;
		this.joc = joc;
	}
	
	public LinkedList<HistoricPartides> getHistoric() {
		return historic;
	}
	public void setHistoric(LinkedList<HistoricPartides> historic) {
		this.historic = historic;
	}
	public int getJoc() {
		return joc;
	}
	public void setJoc(int joc) {
		this.joc = joc;
	}
}
