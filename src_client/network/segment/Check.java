package network.segment;

public class Check extends Segment{
	private static final long serialVersionUID = 1L;
	private boolean ok;
	
	public Check(boolean b) {
		this.ok =b;
	}

	public boolean isOk() {
		return ok;
	}	
}
