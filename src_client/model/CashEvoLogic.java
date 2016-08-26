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

package model;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.LinkedList;

/**
 * The Class CashEvoLogic.
 * (S'encarrega de la part lògica del gràfic de l'evolució de saldo del jugador.)
 */
public class CashEvoLogic {
	private LinkedList<Float> anteriors;
	private static float max;
	private static float min;
	private static float amplada;
	private static float altura;
	private float ric;
	private float factor;
	private boolean flag;

	/**
	 * Instantiates a new cash evo logic.
	 */
	public CashEvoLogic() {
		anteriors = new LinkedList<Float>();
	}

	/**
	 * Find pixel.
	 * (Troba el píxel Y corresponent a partir d'un valor de diners.)
	 *
	 * @param money
	 * @return float
	 */
	public float findPixel(float money) {
		if (money == 0) {
			anteriors.add(min);
			return min;
		} else if (money > ric) {
			anteriors.add(altura - max);
			flag = true;
			factor = (money * max / ric) / max;
			ric = money;
			return altura - max;
		} else {
			anteriors.add(altura - (money * max / ric));
			return altura - (money * max / ric);
		}
	}

	/**
	 * Gets anteriors.
	 *
	 * @return the anteriors
	 */
	public LinkedList<Float> getAnteriors() {
		return anteriors;
	}

	/**
	 * Update list.
	 * (Actualitza la llista que emmagatzema els punts anteriors.)
	 *
	 * @param i
	 * @param newPos
	 */
	public void updateList(int i, float newPos) {
		anteriors.set(i, newPos);
	}

	/**
	 * Find new position.
	 * (Troba la nova posició d'un punt preexistent.)
	 *
	 * @param oldPos
	 * @return float
	 */
	public float findNewPosition(float oldPos) {
		return altura - ((altura - oldPos) / factor);
	}

	/**
	 * Find max.
	 * (Troba el píxel màxim a ocupar.)
	 *
	 * @return float
	 */
	public float findMax() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int h = (int) dim.getHeight();
		return max = altura - h / 12;
	}

	/**
	 * Find factor.
	 * (Determina el factor d'escala per a la reorganització dels punts preexistents.)
	 *
	 * @param oldPos
	 * @return float
	 */
	public float findFactor(float oldPos) {
		return factor = oldPos / max;
	}

	/**
	 * Find width.
	 * (Determina la distància entre punts a partir del seu nombre.)
	 *
	 * @param size
	 * @return float
	 */
	public float findWidth(int size) {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int w = (int) dim.getWidth();
		return amplada = size == 1 ? w : w / (size - 1);
	}

	/**
	 * Sets altura.
	 *
	 * @param h
	 */
	public void setAltura(float h) {
		min = altura = h - 25;
	}

	/**
	 * Gets max.
	 *
	 * @return max
	 */
	public static float getMax() {
		return max;
	}

	/**
	 * Gets factor.
	 *
	 * @return factor
	 */
	public float getFactor() {
		return factor;
	}

	/**
	 * Gets amplada.
	 *
	 * @return amplada
	 */
	public float getAmplada() {
		return amplada;
	}

	/**
	 * Gets ric.
	 *
	 * @return ric
	 */
	public float getRic() {
		return ric;
	}

	/**
	 * Checks if is flag.
	 *
	 * @return true, if is flag
	 */
	public boolean isFlag() {
		return flag;
	}

	/**
	 * Sets flag.
	 *
	 * @param flag
	 */
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
}
