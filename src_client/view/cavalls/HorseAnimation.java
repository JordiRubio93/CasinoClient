package view.cavalls;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 
 * @version 1.0 19/05/2016
 * @author  Pol ValÃ©s - ls30599@salleurl.edu <br/>
 * 			Diego Bellino - ls30741@salleurl.edu <br/>
 * 			Enric Marin - ls31308@salleurl.edu <br/>
 * 			Jordi RubiÃ³ - ls31289@salleurl.edu <br/>
 * 			David Estepa - ls30622@salleurl.edu <br/>
 * 			Disseny i programaciÃ³ orientats a objectes. <br/>
 * 			La Salle - Universitat Ramon Llull. <br/>
 */

public class HorseAnimation {
	private String color;
	private Image gif;
	private int index;
	private String s;
	private int flag;
	
	/**
	 * Constructor
	 * @param color
	 * @param flag
	 */
	public HorseAnimation(String color, int flag){
		this.flag = flag;
		index = 1; //Inicialitzem l'índex al primer frame del sprite
		this.color = color;
		try{
			s = "Resources/horses/" + color + String.valueOf(1) + ".png";
			File fitxer = new File(s);
			gif = ImageIO.read(fitxer);
		}catch(IOException e){
			//e.printStackTrace();
		}
	}
	
	public int getFlag() {
		return flag;
	}

	/**
	 * Procediment per anar movent el cavall.
	 * No és cap bucle, simplement avança el cavall al següent frame i prou (si està al 4, retorna al 1)
	 * Finalment, a través del color i l'índex agafa el recurs que correspon, ja que tenen el nom corresponent.
	 */
	public void run() {
		try {
			if(index == 4) index = 1;
			else index++;
			s = "Resources/horses/" + color + String.valueOf(index) + ".png";
			File fitxer = new File(s);
			gif = ImageIO.read(fitxer);
		} catch (IOException e) {
			//e.printStackTrace();
		}
	}

	public Image getGif() {
		return gif;
	}
}
