package network.segment;

public class ChangePassword extends Segment{
	private static final long serialVersionUID = 1L;
	private String newPassword;
	
	public ChangePassword(String newPassword) {
		super();
		this.newPassword = newPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	



}
