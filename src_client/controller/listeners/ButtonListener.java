package controller.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import controller.Manager;
import view.RegisterWindow;

public class ButtonListener implements ActionListener {
	private Manager manager;
	public ButtonListener(Manager manager) {
		this.manager = manager;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		switch (((JButton) event.getSource()).getClientProperty("action").toString()) {
		case ("Log in"):
			manager.login();
		
			break;
		case ("Register"):
			manager.setPanel(new  RegisterWindow());
			break;

		case ("Play Roulette"):
			manager.comenzarJoc(((JButton) event.getSource()).getClientProperty("action").toString());
			break;
		case ("Play Horses"):
			manager.comenzarJoc(((JButton) event.getSource()).getClientProperty("action").toString());
			break;
		case ("Play Blackjack"):
			manager.comenzarJoc(((JButton) event.getSource()).getClientProperty("action").toString());
			break;
		case ("Stadistics"):
	
			break;
	
		

		
		default:
			System.err.println(((JButton) event.getSource()).getToolTipText());
		}	
	}
}
