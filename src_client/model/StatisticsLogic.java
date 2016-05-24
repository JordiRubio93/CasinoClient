package model;

import java.util.LinkedList;

import model.struct.user.HistoricPartides;

/**
 * 
 * <p>
 * <b> Classe: StaticsLogic </b> <br/>
 * Implementa la logica de les estadistiques.
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
public class StatisticsLogic {
	public int[] calculaValorsY(LinkedList<HistoricPartides> historic){
		int max = (int) historic.getFirst().getGuanys();
		int digits = String.valueOf(max).length();
		int major = (int) (((String.valueOf(max).charAt(0) - '0') + 1) * Math.pow(10,(double)(digits-1)));
		int actual = major;
		
		int[] valorsY = new int[5];

		int index = 0;
		while(actual > Math.pow(10,(double)(digits-1)) && index < 5){
			valorsY[index] = actual;
			actual = (int) (actual - Math.pow(10,(double)(digits-1)));
			index++;
		}
		
		return valorsY;
	}
	
	public static float[] calculaValorsBarres(LinkedList<HistoricPartides> historic){
		float[] valorsBarres = new float[5];
		
		for(int i = 0; i < historic.size() && i < 5; i++){
			valorsBarres[i] = historic.get(i).getGuanys();
		}
		
		return valorsBarres;
	}
}
