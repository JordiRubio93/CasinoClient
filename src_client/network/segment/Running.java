package network.segment;

public class Running extends Segment {
	private static final long serialVersionUID = 1L;
	private boolean running;
	private int joc;
	
	public Running(boolean running, int joc){
		this.running = running;
		this.joc = joc;
	}
	
	public boolean isRunning() {
		return running;
	}
	public void setRunning(boolean running) {
		this.running = running;
	}
	public int getJoc() {
		return joc;
	}
	public void setJoc(int joc) {
		this.joc = joc;
	}
}
