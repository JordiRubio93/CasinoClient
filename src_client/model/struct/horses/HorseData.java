package model.struct.horses;

import java.io.Serializable;

public class HorseData implements Serializable{
	private static final long serialVersionUID = 1L;
	private String name;
	private String color;
	private int dorsal;
	private int segons;
	
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
