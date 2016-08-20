package network.segment;

public class GameOver extends Segment{
	private static final long serialVersionUID = 1L;
	private int joc;

	public GameOver(int joc) {
		this.setJoc(joc);
	}
	public int getJoc() {
		return joc;
	}
	public void setJoc(int joc) {
		this.joc = joc;
	}
}
