package model.struct.horses;

public class HorseData {
	private String name;
	private String color;
	private int dorsal;
	
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
}
