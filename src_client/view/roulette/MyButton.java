package view.roulette;

import java.awt.Color;

import javax.swing.JButton;

/**
 * 
 * <p>
 * <b> Classe: MyButton </b> <br/>
 * Implementa la classe MyButton la qual utilitzarem per tenir els nostres propis botons.
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
public class MyButton extends JButton {
	
	//Atributs de la classe
	private static final long serialVersionUID = 1L;
	private boolean pushed;
	private Color color;
	
	/**
     * Constructor per la classe MyButton.
     */
	public MyButton(String s, Color color) {
		super(s);
		this.color = color;
		this.pushed = true;
	}//Tancament del constructor
	
	/**
     * Metode que no retorna res i que s'encarrega de canviar l'estat d'un boto (si esta clicat o no).
     */
	public void canviaEstat(){
		if(pushed == false) pushed = true;
		else pushed = false;
	}//Tancament del metode
	
	/**
     * Metode que retorna un boolean i que s'encarrega de consultar l'estat d'un boto (si esta clicat o no).
     * @return pushed (un boolean que indica si esta clicat o no)
     * 
     */
	public boolean consultaEstat(){
		return pushed;
	}//Tancament del metode

	/**
     * Metode que retorna un atribut del tipus Color i que s'encarrega d'indicar el color.
     * @return color
     */
	public Color getColor() {
		return color;
	}//Tancament del metode
}
