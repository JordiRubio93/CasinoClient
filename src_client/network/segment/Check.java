




package network.segment;

/**
 * The Class Check.
 */
public class Check extends Segment {
	private static final long serialVersionUID = 1L;
	private boolean ok;

	/**
	 * Instantiates a new Check segment.
	 *
	 * @param b
	 */
	public Check(boolean b) {
		this.ok = b;
	}

	/**
	 * Checks if is ok.
	 *
	 * @return true, if is ok
	 */
	public boolean isOk() {
		return ok;
	}
}
