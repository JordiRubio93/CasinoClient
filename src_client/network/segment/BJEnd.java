package network.segment;

public class BJEnd extends Segment {
	private static final long serialVersionUID = 1L;
	private float guanys;
	private float diners;
	
	public BJEnd(float guanys, float diners) {
		super();
		this.guanys = guanys;
		this.diners = diners;
	}

	public float getGuanys() {
		return guanys;
	}

	public void setGuanys(float guanys) {
		this.guanys = guanys;
	}

	public float getDiners() {
		return diners;
	}

	public void setDiners(float diners) {
		this.diners = diners;
	}
}
