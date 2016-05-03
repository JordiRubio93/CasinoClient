package model.winners;

public class WinnerRoulette extends Winner{
	private static final long serialVersionUID = 1L;
	private int num;
	private String Color;
	
	public WinnerRoulette(int num, String color) {
		this.num = num;
		Color = color;
	}
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getColor() {
		return Color;
	}
	public void setColor(String color) {
		Color = color;
	}
}
