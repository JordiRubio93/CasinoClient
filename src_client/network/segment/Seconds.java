package network.segment;

public class Seconds extends Segment {
	private static final long serialVersionUID = 1L;
	private int segons;

	public Seconds(int segons) {
		this.setSegons(segons);
	}

	public int getSegons() {
		return segons;
	}

	public void setSegons(int segons) {
		this.segons = segons;
	}
}
