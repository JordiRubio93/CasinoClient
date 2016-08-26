/**
 * @author
 * Pol Vales - ls30599@salleurl.edu
 * Enric Marin - ls31308@salleurl.edu
 * Diego Bellino - ls30741@salleurl.edu
 * Jordi Rubio - ls31289@salleurl.edu
 * David Estepa - ls30622@salleurl.edu
 * DPO2 (Disseny i programacio orientats a objectes)
 * La Salle, Universitat Ramon Llull
 */

package view.horses;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * The Class HorseAnimation.
 * (Classe dirigida a les animacions dels cavalls mentre corren.)
 */
public class HorseAnimation {
	private String color;
	private Image gif;
	private int index;
	private String s;
	private int flag;

	/**
	 * Instantiates a new horse animation.
	 *
	 * @param color
	 * @param flag
	 */
	public HorseAnimation(String color, int flag) {
		this.flag = flag;
		index = 1; // Inicialitzem l'índex al primer frame del sprite
		this.color = color;
		try {
			s = "Resources/horses/" + color + String.valueOf(1) + ".png";
			File fitxer = new File(s);
			gif = ImageIO.read(fitxer);
		} catch (IOException e) {
			// e.printStackTrace();
		}
	}

	/**
	 * Gets flag.
	 *
	 * @return flag
	 */
	public int getFlag() {
		return flag;
	}

	/**
	 * (Fa moure les animacions dels cavalls.)
	 */
	public void run() {
		try {
			if (index == 4)
				index = 1;
			else
				index++;
			s = "Resources/horses/" + color + String.valueOf(index) + ".png";
			File fitxer = new File(s);
			gif = ImageIO.read(fitxer);
		} catch (IOException e) {
			// e.printStackTrace();
		}
	}

	/**
	 * Gets gif.
	 *
	 * @return gif
	 */
	public Image getGif() {
		return gif;
	}
}
