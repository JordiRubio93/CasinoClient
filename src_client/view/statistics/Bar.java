package view.statistics;

import java.awt.Color;


/**
 * 
 * @version 1.0 19/05/2016
 * @author  Pol Val√©s - ls30599@salleurl.edu <br/>
 * 			Diego Bellino - ls30741@salleurl.edu <br/>
 * 			Enric Marin - ls31308@salleurl.edu <br/>
 * 			Jordi Rubi√≥ - ls31289@salleurl.edu <br/>
 * 			David Estepa - ls30622@salleurl.edu <br/>
 * 			Disseny i programaci√≥ orientats a objectes. <br/>
 * 			La Salle - Universitat Ramon Llull. <br/>
 */


public class Bar {
	private double value; 
    private Color color;
    private String name;
   
    /**
     * Constructor
     * @param value: valor passat per par‡metre per establir l'alÁada relativa en pÌxels
     * @param color: color que varia (segons Ruleta, Cursa de cavalls i Blackjack)
     * @param name: cadena de car‡cters pel nom de la barra corresponent
     */
    public Bar(int value, Color color, String name) {
        this.value = value;
        this.color = color;
        this.name = name;
    }

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
