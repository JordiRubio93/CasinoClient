package network.segment;

import model.Bet;
import model.struct.user.PublicUser;
/**
 * 
 * <p>
 * <b> Classe: NotifyBet </b> <br/>
 * </p>
 * 
 * Especialitzaci� de segment, per notificar apostes
 * 
 * @version 1.0 19/05/2016
 * @author  Pol Valés - ls30599@salleurl.edu <br/>
 * 			Diego Bellino - ls30741@salleurl.edu <br/>
 * 			Enric Marin - ls31308@salleurl.edu <br/>
 * 			Jordi Rubió - ls31289@salleurl.edu <br/>
 * 			David Estepa - ls30622@salleurl.edu <br/>
 * 			Disseny i programació orientats a objectes. <br/>
 * 			La Salle - Universitat Ramon Llull. <br/>
 * 
 */
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
