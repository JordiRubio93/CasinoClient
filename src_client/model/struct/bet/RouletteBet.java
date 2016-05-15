package model.struct.bet;

import java.util.LinkedList;

public class RouletteBet extends Bet {
	private static final long serialVersionUID = 1L;
	private LinkedList<Integer> numeros;
	
	public RouletteBet(float amount) {
		super(amount);
	}
	
	public LinkedList<Integer> getNumeros() {
		return numeros;
	}
	public void setNumeros(LinkedList<Integer> numeros) {
		this.numeros = numeros;
	}
}
