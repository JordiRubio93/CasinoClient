package controller.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import model.LPanel;
import view.ConfigPanel;


public class LPanelController implements ActionListener{
	private ConfigPanel vista;
	private LPanel lPanel;
	
	
	public LPanelController (ConfigPanel vista) {
		this.vista = vista;
		lPanel = new LPanel();
	}
	
	
	public void actionPerformed(ActionEvent event) {
		JButton boto = (JButton) event.getSource();
		
		if (boto.getText().equals("Add money to account")) {
			System.out.println("Add money to account");

		}
		if (boto.getText().equals("See cash evolution")) {
			System.out.println("See cash evolution");

		}
		
	}
	
}
