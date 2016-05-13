package network.segment;

public class Betting extends Segment{
	private static final long serialVersionUID = 1L;
	private String userName;

	public Betting(String userName) {
		this.setUserName(userName);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
