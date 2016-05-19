package view.roulette;

import java.awt.Color;

import model.struct.bet.RouletteBet;
import view.Dialeg;

/**
 * 
 * <p>
 * <b> Classe: PushedButtons </b> <br/>
 * Implementa tot el que fà referència a quan l'usuari clica un boto per apostar.
 * </p>
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
public class PushedButtons {
	
	//Atributs de la classe
	private static final Color PUSHED = new Color(255,215,0);
	private RouletteBet bet;
	
	/**
     * Metode que no retorna res i que s'encarrega de despintar un boto.
     * @param jBoto
     */
	public void despintaBoto(MyButton jBoto){
		jBoto.canviaEstat();
		jBoto.setBackground(jBoto.getColor());
		bet = null;
	}//Tancament del metode
	
	/**
     * Metode que no retorna res i que s'encarrega de pintar un boto.
     * @param jBoto
     */
	public void pintaBoto(MyButton jBoto){
		Dialeg dialeg = new Dialeg();
		dialeg.setInputText("How much money you want to bet?");
		
		if(dialeg.getAmount() != null && (dialeg.getAmount().isEmpty() || Float.parseFloat(dialeg.getAmount()) <= 0)){
			dialeg.setWarningText("Enter a correct amount!");
			pintaBoto(jBoto);
		}else if (dialeg.getAmount() != null){
			jBoto.canviaEstat();
			jBoto.setBackground(PUSHED);
			bet = new RouletteBet(Float.parseFloat(dialeg.getAmount()));
		}
	}//Tancament del metode
	
	/**
     * Metode que retorna un RouletteBet.
     * @return RouletteBet
     */
	public RouletteBet getBet() {
		return bet;
	}//Tancament del metode
}
