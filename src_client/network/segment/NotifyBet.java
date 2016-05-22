package network.segment;

import model.Bet;
import model.struct.user.PublicUser;

public class NotifyBet extends Segment{

	private static final long serialVersionUID = 1L;
	private PublicUser publicUser;
	private Bet aposta;
	
	public NotifyBet(PublicUser publicUser, Bet aposta) {
		super();
		this.publicUser = publicUser;
		this.aposta = aposta;
	}
	public PublicUser getPublicUser() {
		return publicUser;
	}
	public void setPublicUser(PublicUser publicUser) {
		this.publicUser = publicUser;
	}
	public Bet getAposta() {
		return aposta;
	}
	public void setAposta(Bet aposta) {
		this.aposta = aposta;
	} 
}
