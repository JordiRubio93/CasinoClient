package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JButton;

import model.RegisterValidator;
import model.struct.user.HistoricPartides;
import network.segment.GameOver;
import network.segment.LogOut;
import network.segment.Play;
import network.segment.Top5;
import view.AddMoneyFrame;
import view.Dialeg;
import view.PasswordFrame;
import view.cavalls.HorsesView;
import view.cavalls.PickHorseView;
import view.roulette.MyButton;
import view.statistics.Graphics;

public class MainButtonsController implements ActionListener {
	private Manager manager;
	private PasswordFrame pf;
	private AddMoneyFrame af;
	
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
			if (manager.getGameManager().isGuest()) {
				new Dialeg().setWarningText("You can't play, you're a guest.");
			} else {
				try {
					manager.getServer().enviarTrama(new Play("roulette"));
					manager.showPanel(Constants.R_VIEW_NAME);
					manager.comenzarJoc("Play Roulette", manager.getPanel(Constants.R_VIEW_NAME));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			break;
		case ("Play Horses"):
			if (manager.getGameManager().isGuest()) {
				new Dialeg().setWarningText("You can't play, you're a guest.");
			} else {
				try {
					manager.getServer().enviarTrama(new Play("horses"));
					manager.showPanel(Constants.H_VIEW_NAME);
					manager.comenzarJoc("Play Horses", manager.getPanel(Constants.H_VIEW_NAME));
				} catch (IOException e) {
					e.printStackTrace();
				}
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
			pf = new PasswordFrame(manager);
			pf.setVisible(true);

			break;
		case ("Go Change Password"):
			RegisterValidator rv = new RegisterValidator();
			if (!pf.getPassword().equals(pf.getPassword()) || !rv.validatePasswordFormat(pf.getPassword())) {
				new Dialeg().setWarningText("wrong password");
			} else {
				pf.setVisible(false);
				pf.dispose();
				manager.changePW(pf.getPassword());
			}			
			break;
		case ("Add Money"):
			System.out.println("in");
			af = new AddMoneyFrame(manager);
			af.setVisible(true);
			break;
		case ("Go Add Money"):
			af.setVisible(false);
			af.dispose();
			break;
		case ("User Evo"):
			break;
		case ("Log Out"):
			if (!manager.getGameManager().isGuest()) {
				manager.startServer();
				manager.logout();
				try {
					manager.getServer().enviarTrama(new LogOut());

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			manager.lateralMainPanel(false);
			manager.showPanel("LoginWindow");
			break;
		case ("Home"):
			manager.showPanel("MainWindow");
			break;
		case ("roulette"):
			MyButton boton = ((MyButton) event.getSource());
			manager.getGameManager().thisSlot(boton);
			break;
		case ("Back"):
			manager.showPanel(Constants.STATISTICS_VIEW_NAME);
			manager.getGameManager().executaGrafics(false);
			break;
		case ("Top 5 Roulette"):
			try {
				manager.getServer().enviarTrama(new Top5(null, 1));
				LinkedList<HistoricPartides> histRuleta = ((Top5) manager.getServer().obtenirTrama()).getHistoric();
				if(((Graphics) manager.getPanel(Constants.GRAPHICS_VIEW_NAME)).createChart(histRuleta)){
					manager.showPanel(Constants.GRAPHICS_VIEW_NAME);
					manager.getGameManager().executaGrafics(true);
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case ("Top 5 Blackjack"):
			try {
				manager.getServer().enviarTrama(new Top5(null, 3));
				LinkedList<HistoricPartides> histBJ = ((Top5) manager.getServer().obtenirTrama()).getHistoric();
				if(((Graphics) manager.getPanel(Constants.GRAPHICS_VIEW_NAME)).createChart(histBJ)){
					manager.showPanel(Constants.GRAPHICS_VIEW_NAME);
					manager.getGameManager().executaGrafics(true);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case ("Top 5 Horses"):
			try {
				manager.getServer().enviarTrama(new Top5(null, 2));
				LinkedList<HistoricPartides> histCavalls = ((Top5) manager.getServer().obtenirTrama()).getHistoric();
				if(((Graphics) manager.getPanel(Constants.GRAPHICS_VIEW_NAME)).createChart(histCavalls)){
					manager.showPanel(Constants.GRAPHICS_VIEW_NAME);
					manager.getGameManager().executaGrafics(true);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case ("Cash Evo"):
			break;
		case ("Cash Ranking"):
			break;
		case ("This Horse"):
			manager.getGameManager().thisHorse();
			break;
		case (PickHorseView.previous):
			((HorsesView) manager.getPanel(Constants.H_VIEW_NAME)).getPhv().passaEsquerra();
			break;
		case (PickHorseView.next):
			((HorsesView) manager.getPanel(Constants.H_VIEW_NAME)).getPhv().passaDreta();
			break;
		case ("EXIT_GAME"):
			// Funcio que doni al servidor la informacio necesaria (ganancies)
			manager.showPanel(Constants.MAIN_VIEW_NAME);
			break;
		case ("BET_R"):
			manager.getGameManager().sendRouletteBet();
			break;
		case ("BET_H"):
			((HorsesView) manager.getPanel(Constants.H_VIEW_NAME)).showPhv();
			break;
		case ("EXIT_H"):
			try {
				manager.getServer().enviarTrama(new GameOver());
				manager.showPanel(Constants.MAIN_VIEW_NAME);
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case ("EXIT_R"):
			try {
				manager.getServer().enviarTrama(new GameOver());
				manager.showPanel(Constants.MAIN_VIEW_NAME);
			} catch (IOException e) {
				e.printStackTrace();
			}
			// kill rulete
			break;
		case ("BET_BJ"):
			manager.getGameManager().betBJ();
			break;
		case ("HIT_BJ"):
			manager.getGameManager().hitBJ();
			break;
		case ("STAND_BJ"):
			manager.getGameManager().standBJ();
			;
			break;
		default:
			System.err.println(((JButton) event.getSource()).getToolTipText());
		}

	}
}
