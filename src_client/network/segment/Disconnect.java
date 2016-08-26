




package network.segment;

/**
 * The Class Disconnect.
 */
public class Disconnect extends Segment {
	private static final long serialVersionUID = 1L;
	private boolean led;

	/**
	 * Instantiates a new Disconnect segment.
	 *
	 * @param led
	 */
	public Disconnect(boolean led) {
		this.led = led;
	}

	/**
	 * Checks if is led.
	 *
	 * @return true, if is led
	 */
	public boolean isLed() {
		return led;
	}
}
