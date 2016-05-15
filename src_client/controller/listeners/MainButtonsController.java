package controller.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;

import controller.Manager;
import network.segment.LogOut;
import view.ConfigPanel;
import view.Dialeg;
import view.LoginWindow;
import view.MainWindow;
import view.StatisticsWindow;

public class MainButtonsController implements ActionListener {
	private Manager manager;
	private boolean guest;
	
	public MainButtonsController(Manager manager) {
		this.manager = manager;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		switch (((JButton) event.getSource()).getClientProperty("action").toString()) {
		case ("Log in"):
			manager.login();
			break;
		case ("Try as guest"):
			manager.setPanel(new MainWindow());
			guest = true;
			break;
		case ("Register"):
			manager.setPanel(new MainWindow());
			break;	
		case ("Play Roulette"):
			if(guest) new Dialeg().setWarningText("You can't play, you're a guest.");
			else manager.comenzarJoc(((JButton) event.getSource()).getClientProperty("action").toString());
			break;
		case ("Play Horses"):
			if(guest) new Dialeg().setWarningText("You can't play, you're a guest.");
			else manager.comenzarJoc(((JButton) event.getSource()).getClientProperty("action").toString());
			break;
		case ("Play BlackJack"):
			manager.comenzarJoc(((JButton) event.getSource()).getClientProperty("action").toString());
			break;
		case ("Statistics"):
			manager.setPanel(new StatisticsWindow());
			break;
		case ("Configuration"):
			manager.setPanel(new MainWindow(new ConfigPanel()));
			break;
		case ("Minimize Panel"):
			manager.setPanel(new MainWindow());
			break;
		case ("Change Password"):
			break;
		case ("Add money"):
			break;
		case ("User Evo"):
			break;
		case ("Log Out"):
			guest = false;
			try {
				manager.getServer().enviarTrama(new LogOut());
				manager.setPanel(new LoginWindow());
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case ("Home"):
			manager.setPanel(new MainWindow());
			break;
		case ("Top 5 Roulette"):
			break;
		case ("Top 5 Blackjack"):
			break;
		case ("Top 5 Horses"):
			break;
		case ("Cash Evo"):
			break;
		case ("Cash Ranking"):
			break;
		default:
			System.err.println(((JButton) event.getSource()).getToolTipText());
		}
	}
}
