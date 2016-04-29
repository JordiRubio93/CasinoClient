package model.struct.bet;

public class RouletteBet extends Bet {
	private String color;
	private int num;
	private boolean isColor;
	private String aposta;
	
	public RouletteBet(float amount, String color, int num, boolean isColor) {
		super(amount);
		
		if(this.isColor = isColor) this.color = color;
		else this.num = num;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public boolean isColor() {
		return isColor;
	}

	public void setColor(boolean isColor) {
		this.isColor = isColor;
	}

	public String getAposta() {
		return aposta;
	}

	public void setAposta(String aposta) {
		this.aposta = aposta;
	}
}
