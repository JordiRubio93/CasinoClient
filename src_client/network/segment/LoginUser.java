package network.segment;

import model.struct.user.LoginInfo;
/**
 * 
 * <p>
 * <b> Classe: LoginUser </b> <br/>
 * </p>
 * 
 * Especialitzaci� de segment, per loguejar l'usuari
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
