




package network.segment;

/**
 * The Class BJEnd.
 */
public class BJEnd extends Segment {
	private static final long serialVersionUID = 1L;
	private float guanys;
	private float diners;

	/**
	 * Instantiates a new BJEnd segment.
	 *
	 * @param guanys
	 * @param diners
	 */
	public BJEnd(float guanys, float diners) {
		super();
		this.guanys = guanys;
		this.diners = diners;
	}

	/**
	 * Gets guanys.
	 *
	 * @return guanys
	 */
	public float getGuanys() {
		return guanys;
	}

	/**
	 * Sets guanys.
	 *
	 * @param guanys
	 */
	public void setGuanys(float guanys) {
		this.guanys = guanys;
	}

	/**
	 * Gets diners.
	 *
	 * @return diners
	 */
	public float getDiners() {
		return diners;
	}

	/**
	 * Sets diners.
	 *
	 * @param diners
	 */
	public void setDiners(float diners) {
		this.diners = diners;
	}
}
