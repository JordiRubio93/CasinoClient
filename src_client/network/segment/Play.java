package network.segment;
/**
 * 
 * <p>
 * <b> Classe: Play </b> <br/>
 * </p>
 * 
 * Especialitzaci� de segment, per jugar
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
public class Play extends Segment{

	private static final long serialVersionUID = 1L;
	private String tipus;
	
	
	public Play(String tipus) {
		this.tipus = tipus;
	
	}
	public String getTipus(){
		return tipus;
	}
	
}
