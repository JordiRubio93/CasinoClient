package model.struct.roulette;

import java.awt.Color;
/**
 * 
 * <p>
 * <b> Classe: Casella </b> <br/>
 * Implementa la classe casella de la ruleta.
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
public class Casella {
	//Atributs de la classe
	private int numero;
	private boolean color;
	
	/**
	 * Getter de Numero.
	 */
	public int getNumero() {
		return numero;
	}//Tancament del getter
	/**
	 * Getter de Color.
	 */
	public Color getColor() {
		if (color) return (new Color(139, 0, 0)); 
		else return (new Color(010, 010, 010)); 
	}//Tancament del getter
	
	/**
	 * Constructor de casella.
	 */
	public Casella(int numero, boolean color) {
		super();
		this.numero = numero;
		this.color = color;
		
	}//Tancament del constructor
	
	/**
	 * Metode que retorna un boolean i que s'encarrega d'indicar si el numero es parell o no.
	 * @return numero%2==0
	 */
	public boolean isParell(){
		return numero%2==0;
	}//Tancament del metode
	
	/**
	 * Metode que retorna un boolean i que s'encarrega d'indicar el color de la casella (true vermell, false negre).
	 * @return color
	 */
	public boolean isVermell(){
		return color;
	}
	/**
	 * Metode que retorna un boolean i que s'encarrega d'indicar el color de la casella (true vermell, false negre).
	 * @return color
	 */
	public boolean isNegre(){
		return color;
	}//Tancament del metode
	/**
	 * Metode que retorna un boolean i que s'encarrega d'indicar si el numero es senar o no.
	 * @return numero%2!=0
	 */
	public boolean isSenar(){
		return numero%2!=0;
	}//Tancament del metode
	/**
	 * Metode que retorna un boolean i que s'encarrega d'indicar si el numero es manca.
	 * @return numero<=18
	 */
	public boolean isManca(){
		return numero<=18;
	}//Tancament del metode
	/**
	 * Metode que retorna un boolean i que s'encarrega d'indicar si el numero es passa.
	 * @return numero >18
	 */
	public boolean isPasa(){
		return numero>18;
	}//Tancament del metode
	/**
	 * Metode que retorna un int i que s'encarrega d'indicar la dotzena del numero.
	 * @return int
	 */
	public int Dotzena(){
		if (numero>0 && numero <= 12) return 1;
		if (numero>12 && numero <= 24) return 2;
		if (numero>24 && numero <= 36) return 3;
		if (numero == 0) return 0;
		
		return (-1);
	}//Tancament del metode
}//Tancament de la classe
