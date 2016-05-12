package network.segment;

import java.util.LinkedList;

import model.struct.horses.HorseData;

public class InitHorses extends Segment{
	private static final long serialVersionUID = 1L;
	private LinkedList<HorseData> dades;
	private boolean apostable;
	
	public InitHorses(LinkedList<HorseData> dades) {
		this.dades = dades;
	}

	public LinkedList<HorseData> getList(){
		return dades;
	}
	
	public boolean isApostable(){
		return apostable;
	}
}
