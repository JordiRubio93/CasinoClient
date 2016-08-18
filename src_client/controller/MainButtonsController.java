package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JButton;

import model.RegisterValidator;
import model.struct.user.HistoricPartides;
import network.segment.BJEnd;
import network.segment.CashRanking;
import network.segment.GameOver;
import network.segment.LogOut;
import network.segment.Play;
import network.segment.Top5;
import view.AddMoneyFrame;
import view.Dialeg;
import view.MainWindow;
import view.PasswordFrame;
import view.cashevo.CashEvoView;
import view.cavalls.HorsesView;
import view.cavalls.PickHorseView;
import view.roulette.MyButton;
import view.statistics.CashRankingWindow;
import view.statistics.Graphics;

/**
 * 
 * <p>
 * <b> Classe: MainButtonsController </b> <br/>
 * </p>
 * 
 * @version 1.0 19/05/2016
 * @author  Pol Val√©s - ls30599@salleurl.edu <br/>
 * 			Diego Bellino - ls30741@salleurl.edu <br/>
 * 			Enric Marin - ls31308@salleurl.edu <br/>
 * 			Jordi Rubi√≥ - ls31289@salleurl.edu <br/>
 * 			David Estepa - ls30622@salleurl.edu <br/>
 * 			Disseny i programaci√≥ orientats a objectes. <br/>
 * 			La Salle - Universitat Ramon Llull. <br/>
 * 
 */

public class MainButtonsController implements ActionListener {
	//Atributs de la classe
	private Manager manager;
	private PasswordFrame pf;
	private AddMoneyFrame af;
	private boolean guest;
	private double initBJMoney;
	
	/**
	 * Constructor del MainButtonsController.
	 */
	public MainButtonsController(Manager manager) {
		this.manager = manager;

	}//Tancament del constructor

	@Override
	public void actionPerformed(ActionEvent event) {
		System.out.println(((JButton) event.getSource()).getClientProperty("action").toString());
		switch (((JButton) event.getSource()).getClientProperty("action").toString()) {
		case ("Log in"):
			manager.login();
			break;
		case ("Try as guest"):
			manager.startServer();
			manager.getGameManager().setUser(Constants.guest);
			manager.showPanel(Constants.MAIN_VIEW_NAME);
			((MainWindow) manager.getPanel("MainWindow")).getLateralPanel().setGuest(true);
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
			if (manager.getGameManager().isGuest()) guest = true;
				try {
					manager.getServer().enviarTrama(new Play("blackjack"));
					manager.showPanel(Constants.BJ_VIEW_NAME);
					manager.comenzarJoc("Play BlackJack", manager.getPanel(Constants.BJ_VIEW_NAME));
					initBJMoney = manager.getGameManager().getBlackjack().getCashAmount();
				} catch (IOException e) {
					e.printStackTrace();
				}
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
			af = new AddMoneyFrame(manager);
			af.setVisible(true);
			break;
		case ("Go Add Money"):
			af.setVisible(false);
			if (!(af.getCash() > 0)) {
				new Dialeg().setWarningText("wrong Amount");
			} else {
				af.setVisible(false);
				af.dispose();
				manager.addCash(af.getCash(),af.getPassword());
			}
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
		case ("Back Cash Evo"):
			manager.showPanel(Constants.STATISTICS_VIEW_NAME);
			break;
		case ("Back To Cash Evo"):
			manager.showPanel(Constants.CASH_RANKING_VIEW_NAME);
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
		case ("GLOBAL BUTTON"):
			((CashEvoView) manager.getPanel(Constants.CASH_EVO_VIEW_NAME)).setLines(false, 1);
			((CashEvoView) manager.getPanel(Constants.CASH_EVO_VIEW_NAME)).setLines(false, 2);
			((CashEvoView) manager.getPanel(Constants.CASH_EVO_VIEW_NAME)).setLines(false, 3);
			((CashEvoView) manager.getPanel(Constants.CASH_EVO_VIEW_NAME)).setLines(true, 0);
			break;
		case ("ROULETTE BUTTON"):
			((CashEvoView) manager.getPanel(Constants.CASH_EVO_VIEW_NAME)).setLines(false, 0);
			((CashEvoView) manager.getPanel(Constants.CASH_EVO_VIEW_NAME)).setLines(false, 2);
			((CashEvoView) manager.getPanel(Constants.CASH_EVO_VIEW_NAME)).setLines(false, 3);
			((CashEvoView) manager.getPanel(Constants.CASH_EVO_VIEW_NAME)).setLines(true, 1);
			break;
		case ("HORSES BUTTON"):
			((CashEvoView) manager.getPanel(Constants.CASH_EVO_VIEW_NAME)).setLines(false, 0);
			((CashEvoView) manager.getPanel(Constants.CASH_EVO_VIEW_NAME)).setLines(false, 1);
			((CashEvoView) manager.getPanel(Constants.CASH_EVO_VIEW_NAME)).setLines(false, 3);
			((CashEvoView) manager.getPanel(Constants.CASH_EVO_VIEW_NAME)).setLines(true, 2);
			break;
		case ("BJ BUTTON"):
			((CashEvoView) manager.getPanel(Constants.CASH_EVO_VIEW_NAME)).setLines(false, 0);
			((CashEvoView) manager.getPanel(Constants.CASH_EVO_VIEW_NAME)).setLines(false, 1);
			((CashEvoView) manager.getPanel(Constants.CASH_EVO_VIEW_NAME)).setLines(false, 2);
			((CashEvoView) manager.getPanel(Constants.CASH_EVO_VIEW_NAME)).setLines(true, 3);
			break;
		case ("Cash Evo"):
			//...
			break;
		case ("Cash Ranking"):
			try {
				// Envia peticio
				manager.getServer().enviarTrama(new CashRanking(null));
				// Rep les dades
				ArrayList<Object[]> data = ((CashRanking) manager.getServer().obtenirTrama()).getData();
				// Envia el conjunt de dades complet (amb ID)
				manager.getRowListener().setData(data);
				// Crea l'array p˙blic, les dades del qual es mostraran
				ArrayList<Object[]> publicData = new ArrayList<Object[]>();
				// Filtra les dades
				for(int i = 0; i < data.size(); i++){
					Object[] row = new Object[3];
					System.arraycopy(data.get(i), 1, row, 0, 3);
					publicData.add(row);
				}
				// Passa les dades p˙bliques
				((CashRankingWindow) manager.getPanel(Constants.CASH_RANKING_VIEW_NAME)).setData(publicData);
				// Mostra el panell
				manager.showPanel(Constants.CASH_RANKING_VIEW_NAME);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
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
			manager.showPanel(Constants.MAIN_VIEW_NAME);
		
			double diners = manager.getGameManager().getBlackjack().getCashAmount();
			double guanys = 0;
			if (diners > initBJMoney) guanys = diners - initBJMoney;
			
			try {
				if(!guest) manager.getServer().enviarTrama(new BJEnd((float) guanys, (float) diners));
			} catch (IOException e1) {e1.printStackTrace();}
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
			// kill roullette
			break;
		case ("BET_BJ"):
			manager.getGameManager().betBJ(guest);
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

	}//Tancament del metode
	
}//Tancament de la classe
