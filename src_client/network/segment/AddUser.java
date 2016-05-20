package network.segment;

import model.struct.user.User;

public class AddUser extends Segment{
	private static final long serialVersionUID = 1L;
	private User user;
	
	public AddUser(User user) {
		this.user =user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
