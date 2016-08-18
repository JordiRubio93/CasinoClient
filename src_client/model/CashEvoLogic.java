package model;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.LinkedList;

public class CashEvoLogic {
	private LinkedList<Float> anteriors;
	private static float max;
	private static float min;
	private static float amplada;
	private static float altura;
	private float ric;
	private float factor;
	private boolean flag;
	
	public CashEvoLogic(){
		anteriors = new LinkedList<Float>();
	}
	
	public float findPixel(float money){
		if(money == 0){
			anteriors.add(min);
			return min;
		}else if(money > ric){
			anteriors.add(altura-max);
			flag = true;
			factor = (money*max/ric)/max;
			ric = money;
			return altura - max;
		}else{
			anteriors.add(altura - (money*max/ric));
			return altura - (money*max/ric);
		}
	}
	
	public LinkedList<Float> getAnteriors() {
		return anteriors;
	}

	public void updateList(int i, float newPos){
		anteriors.set(i, newPos);
	}
	
	public float findNewPosition(float oldPos){
		return altura - ((altura-oldPos)/factor);
	}
	
	public float findMax(){
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int h = (int) dim.getHeight();
		return max = altura - h/12;
	}
	
	public float findFactor(float oldPos){
		return factor = oldPos/max;
	}
	
	public float findWidth(int size){
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int w = (int) dim.getWidth();
		return amplada = size == 1? w : w/(size-1);
	}
	
	public void setAltura(float h){
		altura = h;
	}
	
	public static float getMax() {
		return max;
	}
	public float getFactor() {
		return factor;
	}
	public float getAmplada() {
		return amplada;
	}
	public float getRic() {
		return ric;
	}

	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
}
