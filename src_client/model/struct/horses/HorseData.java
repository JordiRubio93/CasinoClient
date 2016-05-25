package model.struct.horses;

import java.io.Serializable;

/**
 * @version 1.0 19/05/2016
 * @author  Pol Valés - ls30599@salleurl.edu <br/>
 * 			Diego Bellino - ls30741@salleurl.edu <br/>
 * 			Enric Marín - ls31308@salleurl.edu <br/>
 * 			Jordi Rubió - ls31289@salleurl.edu <br/>
 * 			David Estepa - ls30622@salleurl.edu <br/>
 * 			Disseny i programació orientats a objectes. <br/>
 * 			La Salle - Universitat Ramon Llull. <br/>
 */

public class HorseData implements Serializable{
	private static final long serialVersionUID = 1L;
	private String name;
	private String color;
	private int dorsal;
	private int segons;
	
	/**
	 * Constructor
	 * @param name: nom identificador del cavall
	 * @param color: color que correspondrà al sprite
	 * @param dorsal: un altre identificador únic
	 */
	public HorseData(String name, String color, int dorsal) {
		this.name = name;
		this.color = color;
		this.dorsal = dorsal;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getDorsal() {
		return dorsal;
	}
	public void setDorsal(int dorsal) {
		this.dorsal = dorsal;
	}
	public int getSegons() {
		return segons;
	}
	public void setSegons(int segons) {
		this.segons = segons;
	}
}
