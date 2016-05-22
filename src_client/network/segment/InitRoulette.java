package network.segment;

public class InitRoulette extends Segment {
	private static final long serialVersionUID = 1L;
	private double guanys;
	private String winner;
	
	public InitRoulette(String winner, double guanys) {
		super();
		this.winner = winner;
		this.guanys = guanys;
	}
	public double getGuanys() {
		return guanys;
	}
	public void setGuanys(double guanys) {
		this.guanys = guanys;
	}
	public String getWinner() {
		return winner;
	}
	public void setWinner(String winner) {
		this.winner = winner;
	}

	
}
