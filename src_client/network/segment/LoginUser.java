package network.segment;

import model.struct.user.User;

public class LoginUser extends Segment{

	private static final long serialVersionUID = 1L;
	private User u;

	public LoginUser(User u){
		this.u = u;
		
	}

	public User getU() {
		return u;
	}

	public void setU(User u) {
		this.u = u;
	}
	
	
}
