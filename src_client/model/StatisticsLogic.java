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

import java.util.LinkedList;

import model.struct.user.HistoricPartides;

/**
 * The Class StatisticsLogic.
 */
public class StatisticsLogic {

	/**
	 * (Calcula els valors Y de l'eix d'ordenades.)
	 *
	 * @param historic
	 * @return int[]
	 */
	public int[] calculaValorsY(LinkedList<HistoricPartides> historic) {
		int max = (int) historic.getFirst().getGuanys();
		int digits = String.valueOf(max).length();
		int major = (int) (((String.valueOf(max).charAt(0) - '0') + 1) * Math.pow(10, (double) (digits - 1)));
		int actual = major;

		int[] valorsY = new int[5];

		int index = 0;
		while (actual > Math.pow(10, (double) (digits - 1)) && index < 5) {
			valorsY[index] = actual;
			actual = (int) (actual - Math.pow(10, (double) (digits - 1)));
			index++;
		}

		return valorsY;
	}

	/**
	 * (Calcula els valors per les barres.)
	 *
	 * @param historic
	 * @return float[]
	 */
	public static float[] calculaValorsBarres(LinkedList<HistoricPartides> historic) {
		float[] valorsBarres = new float[5];

		for (int i = 0; i < historic.size() && i < 5; i++) {
			valorsBarres[i] = historic.get(i).getGuanys();
		}

		return valorsBarres;
	}
}
