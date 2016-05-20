package network.segment;

import model.struct.user.PublicUser;

public class NotifyConRoom extends Segment{

	private static final long serialVersionUID = 1L;
	private PublicUser pu;
	
	public NotifyConRoom(PublicUser pu) {
		this.pu = pu;
	}

	public PublicUser getPu() {
		return pu;
	}

	public void setPu(PublicUser pu) {
		this.pu = pu;
	}
	
	
	
	
}
