package network.segment;
/**
 * 
 * <p>
 * <b> Classe: Seconds </b> <br/>
 * </p>
 * 
 * Especialitzaci� de segment
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
public class Seconds extends Segment {
	private static final long serialVersionUID = 1L;
	private int segons;

	public Seconds(int segons) {
		this.setSegons(segons);
	}

	public int getSegons() {
		return segons;
	}

	public void setSegons(int segons) {
		this.segons = segons;
	}
}
