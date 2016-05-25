package network.segment;

import model.struct.user.User;
/**
 * 
 * <p>
 * <b> Classe: AddUser </b> <br/>
 * </p>
 * 
 * Especialitzaci� de segment, per enviar usuaris
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
