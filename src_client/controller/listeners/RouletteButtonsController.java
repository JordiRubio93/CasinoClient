package controller.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.LinkedList;

import controller.roulette.RouletteManager;
import model.struct.bet.RouletteBet;
import view.roulette.MyButton;
import view.roulette.PushedButtons;

public class RouletteButtonsController implements ActionListener{
	private RouletteManager rm;
	private MyButton boto;

	public RouletteButtonsController(RouletteManager rm){
		this.rm = rm;
	}
	
	public void actionPerformed(ActionEvent event) {
		boto = (MyButton) event.getSource();
		
		PushedButtons pB = new PushedButtons();
		
		if(boto.consultaEstat()){
			pB.pintaBoto(boto);
			
			if(pB.getBet() != null){
				RouletteBet bet = pB.getBet();
				bet.setNumeros(estableixLlista());
				
				rm.afegeixAposta(bet);
			}
		}
		else{
			pB.despintaBoto(boto);
			
			RouletteBet bet = new RouletteBet(0);
			bet.setNumeros(estableixLlista());
			
			rm.eliminaAposta(bet);
		}
	}
	
	private LinkedList<Integer> estableixLlista(){
		LinkedList<Integer> numeros = new LinkedList<Integer>();
		
		if (boto.getText().equals("0")){
			numeros.add(0);
		}
		for (int i = 1; i < 37; i++){
			if (boto.getText().equals(""+i)){
				numeros.add(i);
			}
		}
		if (boto.getText().equals("RED")) {
			numeros.addAll(Arrays.asList(3,9,12,18,21,27,30,36,5,14,23,32,1,7,16,19,25,34));
		}
		if (boto.getText().equals("BLACK")) {
			numeros.addAll(Arrays.asList(6,15,24,33,1,8,11,17,20,26,29,35,4,10,13,22,28,31));
		}
		if (boto.getText().equals("1-18")) {
			for(int i = 1; i < 19; i++){
				numeros.add(i);
			}
		}
		if (boto.getText().equals("19-36")) {
			for(int i = 19; i < 37; i++){
				numeros.add(i);
			}
		}
		if (boto.getText().equals("EVEN")) {
			for(int i = 1; i < 37; i++){
				if(i%2 == 0) numeros.add(i);
			}
		}
		if (boto.getText().equals("ODD")) {
			for(int i = 1; i < 37; i++){
				if(i%2 == 1) numeros.add(i);
			}
		}
		if (boto.getText().equals("1ST 12")) {
			for(int i = 19; i < 13; i++){
				numeros.add(i);
			}
		}
		if (boto.getText().equals("2ND 12")) {
			for(int i = 13; i < 25; i++){
				numeros.add(i);
			}
		}
		if (boto.getText().equals("3RD 12")) {
			for(int i = 25; i < 37; i++){
				numeros.add(i);
			}
		}
		
		return numeros;
	}
}
