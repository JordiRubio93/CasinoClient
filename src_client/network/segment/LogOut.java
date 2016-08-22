package network.segment;

public class LogOut extends Segment{
	private static final long serialVersionUID = 1L;
	private boolean led;
	
	public LogOut(boolean led) {
		this.led = led;
	}

	public boolean isLed() {
		return led;
	}	
}
