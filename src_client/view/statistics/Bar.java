package view.statistics;

import java.awt.Color;

public class Bar {
	private double value; 
    private Color color;
    private String name;
    
    public Bar(int value, Color color, String name) {
        this.value = value;
        this.color = color;
        this.name = name;
    }

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
