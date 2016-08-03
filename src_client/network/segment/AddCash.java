package network.segment;
/**
 * 
 * <p>
 * <b> Classe: ChangePassword </b> <br/>
 * </p>
 * 
 * Especialitzaci� de segment, per canviar el password
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
public class AddCash extends Segment{
	private static final long serialVersionUID = 1L;
	private float cash;
	private String password;
	
	
	public AddCash(float cash, String password) {
		super();
		this.cash = cash;
		this.password = password;
	}
	public float getCash() {
		return cash;
	}
	public void setCash(float cash) {
		this.cash = cash;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
