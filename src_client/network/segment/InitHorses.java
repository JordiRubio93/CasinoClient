package network.segment;

import java.util.LinkedList;

import model.struct.horses.HorseData;

public class InitHorses extends Segment{
	private static final long serialVersionUID = 1L;
	private LinkedList<HorseData> dades;
	private double guanys;
	
	public InitHorses(LinkedList<HorseData> dades, double apostable) {
		this.guanys = apostable;
		this.dades = dades;
	}

	public LinkedList<HorseData> getList(){
		return dades;
	}
	
	public double isApostable(){
		return guanys;
	}
}
