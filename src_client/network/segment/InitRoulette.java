package network.segment;
/**
 * 
 * <p>
 * <b> Classe: InitRoulette </b> <br/>
 * </p>
 * 
 * Especialitzaci� de segment, per inicialitzar la ruleta
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
public class InitRoulette extends Segment {
	private static final long serialVersionUID = 1L;
	private double guanys;
	private String winner;
	
	public InitRoulette(String winner, double guanys) {
		super();
		this.winner = winner;
		this.guanys = guanys;
	}
	public double getGuanys() {
		return guanys;
	}
	public void setGuanys(double guanys) {
		this.guanys = guanys;
	}
	public String getWinner() {
		return winner;
	}
	public void setWinner(String winner) {
		this.winner = winner;
	}

	
}
