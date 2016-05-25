package network.segment;

import java.util.LinkedList;

import model.struct.horses.HorseData;
/**
 * 
 * <p>
 * <b> Classe: InitHorses </b> <br/>
 * </p>
 * 
 * Especialitzaci� de segment, per inicialitzar els cavalls
 * 
 * @version 1.0 19/05/2016
 * @author  Pol Valés - ls30599@salleurl.edu <br/>
 * 			Diego Bellino - ls30741@salleurl.edu <br/>
 * 			Enric Marin - ls31308@salleurl.edu <br/>
 * 			Jordi Rubió - ls31289@salleurl.edu <br/>
 * 			David Estepa - ls30622@salleurl.edu <br/>
 * 			Disseny i programació orientats a objectes. <br/>
 * 			La Salle - Universitat Ramon Llull. <br/>
 * 
 */
public class InitHorses extends Segment{
	private static final long serialVersionUID = 1L;
	private LinkedList<HorseData> dades;
	private double guanys;
	
	public InitHorses(LinkedList<HorseData> dades, double guanys) {
		this.guanys = guanys;
		this.dades = dades;
	}

	public LinkedList<HorseData> getList(){
		return dades;
	}
	
	public double getGuanys(){
		return guanys;
	}
}
