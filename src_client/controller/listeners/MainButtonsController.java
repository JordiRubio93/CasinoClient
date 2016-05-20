package controller.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JButton;

import controller.Constants;
import controller.Manager;
import model.struct.user.HistoricPartides;
import network.segment.LogOut;
import network.segment.Top5;
import view.Dialeg;
import view.statistics.Graphics;

public class MainButtonsController implements ActionListener {
	private Manager manager;
	
	public MainButtonsController(Manager manager) {
		this.manager = manager;
	}	
	@Override
	public void actionPerformed(ActionEvent event) {
		System.out.println(((JButton) event.getSource()).getClientProperty("action").toString());
		switch (((JButton) event.getSource()).getClientProperty("action").toString()) {
		case ("Log in"):
			manager.login();
			break;
		case ("Try as guest"):
			manager.getGameManager().setUser(Constants.guest);
			manager.showPanel(Constants.MAIN_VIEW_NAME);
			break;
		case ("Register"):
			manager.register();
			break;
		case ("Play Roulette"):
			if (!manager.getGameManager().isGuest()){
				new Dialeg().setWarningText("You can't play, you're a guest.");
			}else{
				manager.comenzarJoc("Play Roulette", manager.getPanel(Constants.R_VIEW_NAME));
				manager.showPanel(Constants.R_VIEW_NAME);
			}
			break;
		case ("Play Horses"):
			if (!manager.getGameManager().isGuest()){
				new Dialeg().setWarningText("You can't play, you're a guest.");
			}else{
				manager.comenzarJoc("Play Horses", manager.getPanel(Constants.H_VIEW_NAME));
				manager.showPanel(Constants.H_VIEW_NAME);
			}
			break;
		case ("Play BlackJack"):
			manager.showPanel(Constants.BJ_VIEW_NAME);
			manager.comenzarJoc("Play BlackJack", manager.getPanel(Constants.BJ_VIEW_NAME));
			break;
		case ("Statistics"):
			manager.showPanel(Constants.STATISTICS_VIEW_NAME);
			break;
		case ("Configuration"):
			manager.lateralMainPanel(true);
			break;
		case ("Minimize Panel"):
			manager.lateralMainPanel(false);
			break;
		case ("Change Password"):
			break;
		case ("Add money"):
			break;
		case ("User Evo"):
			break;
		case ("Log Out"):
			manager.lateralMainPanel(false);
			manager.logout();
			try {
				manager.getServer().enviarTrama(new LogOut());
				manager.showPanel("LoginWindow");
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case ("Home"):
			manager.showPanel("MainWindow");
			break;
		case ("Back"):
			manager.showPanel(Constants.STATISTICS_VIEW_NAME);
			break;
		case ("Top 5 Roulette"):
			try {
				manager.getServer().enviarTrama(new Top5(null, 1));
				LinkedList<HistoricPartides> histRuleta = ((Top5) manager.getServer().obtenirTrama()).getHistoric();
				if(((Graphics) manager.getPanel(Constants.GRAPHICS_VIEW_NAME)).createChart(histRuleta)){
					manager.showPanel(Constants.GRAPHICS_VIEW_NAME);
					new Thread(((Graphics)manager.getPanel(Constants.GRAPHICS_VIEW_NAME)).getChart()).start();
				}
			} catch (IOException e) {
			}
			break;
		case ("Top 5 Blackjack"):
			try {
				manager.getServer().enviarTrama(new Top5(null, 3));
				LinkedList<HistoricPartides> histBJ = ((Top5) manager.getServer().obtenirTrama()).getHistoric();
				if(((Graphics) manager.getPanel(Constants.GRAPHICS_VIEW_NAME)).createChart(histBJ)){
					manager.showPanel(Constants.GRAPHICS_VIEW_NAME);
					new Thread(((Graphics)manager.getPanel(Constants.GRAPHICS_VIEW_NAME)).getChart()).start();
				}
			} catch (IOException e) {
			}
			break;
		case ("Top 5 Horses"):
			try {
				manager.getServer().enviarTrama(new Top5(null, 2));
				LinkedList<HistoricPartides> histCavalls = ((Top5) manager.getServer().obtenirTrama()).getHistoric();
				if(((Graphics) manager.getPanel(Constants.GRAPHICS_VIEW_NAME)).createChart(histCavalls)){
					manager.showPanel(Constants.GRAPHICS_VIEW_NAME);
					new Thread(((Graphics)manager.getPanel(Constants.GRAPHICS_VIEW_NAME)).getChart()).start();
				}
			} catch (IOException e) {
			}
			break;
		case ("Cash Evo"):
			break;
		case ("Cash Ranking"):
			break;
		case ("EXIT_GAME"):
			//JOptionPane.showMessageDialog(f, "See you soon!", "* GOOD BYE *", JOptionPane.PLAIN_MESSAGE);
			// Funcio que doni al servidor la informacio necesaria (ganancies)
			manager.showPanel(Constants.MAIN_VIEW_NAME);
			//TODO parar les funcions dels jocs
			break;
		case ("BET_BJ"):
			manager.getGameManager().betBJ();
			break;
		case ("HIT_BJ"):
			manager.getGameManager().hitBJ();
			break;
		case ("STAND_BJ"):
			manager.getGameManager().standBJ();;
			break;
			
			
			
			
		default:
			System.err.println(((JButton) event.getSource()).getToolTipText());
		}
		
		
	}
}
