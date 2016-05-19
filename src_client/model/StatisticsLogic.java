package model;

import java.util.LinkedList;

import model.struct.user.HistoricPartides;

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
