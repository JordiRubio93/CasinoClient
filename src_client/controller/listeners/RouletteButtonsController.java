package controller.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.roulette.MyButton;
import view.roulette.PushedButtons;

public class RouletteButtonsController implements ActionListener{
	
	public void actionPerformed(ActionEvent event) {
		MyButton boto = (MyButton) event.getSource();
		
		PushedButtons pB = new PushedButtons();
		
		if(boto.consultaEstat()) pB.pintaBoto(boto);
		else pB.despintaBoto(boto);
		
		if (boto.getText().equals("0")) {
			System.out.println("has clicat al 0");
		}
		
		for (int i = 1; i < 37; i++){
			if (boto.getText().equals(""+i)){
				System.out.println("has clicat al "+i);
			}
		}
		
		if (boto.getText().equals("RED")) {
			System.out.println("has clicat al vermell");
		}
		if (boto.getText().equals("BLACK")) {
			System.out.println("has clicat al negre");
		}
		if (boto.getText().equals("1-18")) {
			System.out.println("has clicat al manca");
		}
		if (boto.getText().equals("19-36")) {
			System.out.println("has clicat al passa");
		}
		if (boto.getText().equals("EVEN")) {
			System.out.println("has clicat al even");
		}
		if (boto.getText().equals("ODD")) {
			System.out.println("has clicat al odd");
		}
		if (boto.getText().equals("1ST 12")) {
			System.out.println("has clicat al 1ST 12");
		}
		if (boto.getText().equals("2ND 12")) {
			System.out.println("has clicat al 2ND 12");
		}
		if (boto.getText().equals("3RD 12")) {
			System.out.println("has clicat al 3RD 12");
		}
	}
}
