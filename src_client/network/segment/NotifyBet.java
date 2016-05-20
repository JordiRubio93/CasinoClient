package network.segment;

import model.struct.user.PublicUser;

public class NotifyBet extends Segment{

	private static final long serialVersionUID = 1L;
	private PublicUser publicUser;
	private double amount;
	
	public NotifyBet(PublicUser publicUser, double amount) {
		super();
		this.publicUser = publicUser;
		this.amount = amount;
	}
	public PublicUser getPublicUser() {
		return publicUser;
	}
	public void setPublicUser(PublicUser publicUser) {
		this.publicUser = publicUser;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
}
