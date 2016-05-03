package controller.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ListenerBotons implements ActionListener{
	
	public void actionPerformed(ActionEvent event) {
		JButton boto = (JButton) event.getSource();
		
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
