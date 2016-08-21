package network.segment;

public class Disconnect extends Segment{
	private static final long serialVersionUID = 1L;
	private boolean led;
	
	public Disconnect(boolean led) {
		this.led = led;
	}

	public boolean isLed() {
		return led;
	}	
}
