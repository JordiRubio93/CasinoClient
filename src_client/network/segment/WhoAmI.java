package network.segment;

public class WhoAmI extends Segment {
	private static final long serialVersionUID = 1L;
	private String id;

	public WhoAmI(String id) {
		this.setId(id);
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
