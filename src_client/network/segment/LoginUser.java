package network.segment;

import model.struct.user.LoginInfo;

public class LoginUser extends Segment{

	private static final long serialVersionUID = 1L;
	private LoginInfo u;
	
	
	public LoginUser(LoginInfo u){
		this.u = u;
	}

	public LoginInfo getU() {
		return u;
	}

	public void setU(LoginInfo u) {
		this.u = u;
	}
	
	
}
