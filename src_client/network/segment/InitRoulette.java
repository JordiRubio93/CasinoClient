package network.segment;

public class InitRoulette extends Segment {
	private static final long serialVersionUID = 1L;
	private int num;

	public InitRoulette(int num) {
		this.num = num;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
}
