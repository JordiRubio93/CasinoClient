package model;
/**
 * 
 * <p>
 * <b> Classe: Calcul </b> <br/>
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
public class Calcul {
	public static int calculaY(int i){
		return i*80 - 2;
	}
	
	public static int calculaX(int sec, boolean ct){
		return (sec+10)*5;
	}
}
