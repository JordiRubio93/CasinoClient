package model.struct.roulette;

import java.awt.Color;

public class Casella {
	private int numero;
	private boolean color;
	
	public int getNumero() {
		return numero;
	}
	public Color getColor() {
		if (color) return (new Color(139, 0, 0)); 
		else return (new Color(010, 010, 010)); 
	}
	public Casella(int numero, boolean color) {
		super();
		this.numero = numero;
		this.color = color;
		
	}
	public boolean isParell(){
		return numero%2==0;
	}
	public boolean isVermell(){
		return color;
	}
	public boolean isNegre(){
		return color;
	}
	public boolean isSenar(){
		return numero%2!=0;
	}
	public boolean isManca(){
		return numero<=18;
	}
	public boolean isPasa(){
		return numero>18;
	}
	public int Dotzena(){
		if (numero>0 && numero <= 12) return 1;
		if (numero>12 && numero <= 24) return 1;
		if (numero>24 && numero <= 36) return 1;
		if (numero == 0) return 0;
		
		return (-1);
	}
}
