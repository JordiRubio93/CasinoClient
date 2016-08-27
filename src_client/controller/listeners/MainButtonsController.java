/**
 * @author
 * Pol Vales - ls30599@salleurl.edu
 * Enric Marin - ls31308@salleurl.edu
 * Diego Bellino - ls30741@salleurl.edu
 * Jordi Rubio - ls31289@salleurl.edu
 * David Estepa - ls30622@salleurl.edu
 * DPO2 (Disseny i programacio orientats a objectes)
 * La Salle, Universitat Ramon Llull
 */

package controller.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JButton;

import controller.CashEvoExecutor;
import controller.Constants;
import controller.Manager;
import model.Predictor;
import model.RegisterValidator;
import model.struct.user.HistoricPartides;
import model.struct.user.User;
import network.segment.BJEnd;
import network.segment.CashRanking;
import network.segment.LogOut;
import network.segment.Play;
import network.segment.Running;
import network.segment.Top5;
import network.segment.UserWanted;
import view.AddMoneyFrame;
import view.Dialeg;
import view.MainWindow;
import view.PasswordFrame;
import view.cashevo.CashEvoView;
import view.horses.HorsesView;
import view.horses.PickHorseView;
import view.roulette.MyButton;
import view.statistics.CashRankingWindow;
import view.statistics.Graphics;

/**
 * The Class MainButtonsController.
 * (Gestiona tots els botons de l'interfície gràfica del client.)
 */
public class MainButtonsController implements ActionListener {
	// Atributs de la classe
	private Manager manager;
	private PasswordFrame pf;
	private AddMoneyFrame af;
	private boolean guest;
	private double initBJMoney;

	/**
	 * Instantiates a new main buttons controller.
	 *
	 * @param manager
	 */
	public MainButtonsController(Manager manager) {
		this.manager = manager;
	}// Tancament del constructor

	/**
	 * (Segons el botó polsat, realitza unes instruccions o unes altres.)
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		// System.out.println(((JButton)
		// event.getSource()).getClientProperty("action").toString());
		switch (((JButton) event.getSource()).getClientProperty("action").toString()) {
		case ("Log in"):
			manager.login();
			break;
		case ("Try as guest"):
			guest = true;
			manager.startServer();
			manager.getGameManager().setUser(Constants.guest);
			manager.showPanel(Constants.MAIN_VIEW_NAME);
			((MainWindow) manager.getPanel("MainWindow")).getLateralPanel().setGuest(true);
			((MainWindow) manager.getPanel("MainWindow")).getLateralPanel()
					.setLabels(manager.getGameManager().getUser(), true);
			break;
		case ("Register"):
			manager.register();
			break;
		case ("Play Roulette"):
			if (manager.getGameManager().isGuest()) {
				new Dialeg().setWarningText("You can't play, you're a guest.");
			} else {
				try {
					manager.getServer().enviarTrama(new Running(false, 1));
					if (((Running) manager.getServer().obtenirTrama()).isRunning()) {
						manager.getServer().enviarTrama(new Play("roulette"));
						manager.showPanel(Constants.R_VIEW_NAME);
						manager.comenzarJoc("Play Roulette", manager.getPanel(Constants.R_VIEW_NAME));
					} else {
						new Dialeg().setWarningText("Try again later.");
					}
				} catch (IOException e) {
					// e.printStackTrace();
				}
			}
			break;
		case ("Play Horses"):
			if (manager.getGameManager().isGuest()) {
				new Dialeg().setWarningText("You can't play, you're a guest.");
			} else {
				try {
					manager.getServer().enviarTrama(new Running(false, 2));
					if (((Running) manager.getServer().obtenirTrama()).isRunning()) {
						manager.getServer().enviarTrama(new Play("horses"));
						manager.showPanel(Constants.H_VIEW_NAME);
						manager.comenzarJoc("Play Horses", manager.getPanel(Constants.H_VIEW_NAME));
					} else {
						new Dialeg().setWarningText("Try again later.");
					}
				} catch (IOException e) {
					// e.printStackTrace();
				}
			}
			break;
		case ("Play BlackJack"):
			if (manager.getGameManager().isGuest())
				guest = true;
			try {
				manager.getServer().enviarTrama(new Play("blackjack"));
				manager.showPanel(Constants.BJ_VIEW_NAME);
				manager.comenzarJoc("Play BlackJack", manager.getPanel(Constants.BJ_VIEW_NAME));
				initBJMoney = manager.getGameManager().getBlackjack().getCashAmount();
			} catch (IOException e) {
				// e.printStackTrace();
			}
			break;
		case ("Statistics"):
			manager.showPanel(Constants.STATISTICS_VIEW_NAME);
			break;
		case ("Configuration"):
			manager.lateralMainPanel(true);

			if (guest)
				((MainWindow) manager.getPanel(Constants.MAIN_VIEW_NAME)).getLateralPanel()
						.setLabels(manager.getGameManager().getUser(), true);
			else
				((MainWindow) manager.getPanel(Constants.MAIN_VIEW_NAME)).getLateralPanel()
						.setLabels(manager.getGameManager().getUser(), false);
			break;
		case ("Minimize Panel"):
			manager.lateralMainPanel(false);
			break;
		case ("Change Password"):
			pf = new PasswordFrame(manager);
			pf.setVisible(true);
			manager.getView().setEnabled(false);
			break;
		case ("Go Change Password"):
			RegisterValidator rv = new RegisterValidator();
			if (!pf.getPassword().equals(pf.getPassword()) || !rv.validatePasswordFormat(pf.getPassword())) {
				new Dialeg().setWarningText("Wrong password");
			} else {
				pf.setVisible(false);
				pf.dispose();
				manager.getView().setEnabled(true);
				manager.getView().setVisible(true);
				manager.changePW(pf.getPassword());
				break;
			}
			break;
		case ("Add Money"):
			af = new AddMoneyFrame(manager);
			af.setVisible(true);
			manager.getView().setEnabled(false);
			break;
		case ("Go Add Money"):
			if (!(af.getCash() > 0)) {
				new Dialeg().setWarningText("Wrong amount");
			} else {
				af.setVisible(false);
				af.dispose();
				manager.getView().setEnabled(true);
				manager.getView().setVisible(true);
				manager.addCash(af.getCash(), af.getPassword());
			}

			if (guest)
				((MainWindow) manager.getPanel(Constants.MAIN_VIEW_NAME)).getLateralPanel()
						.setLabels(manager.getGameManager().getUser(), true);
			else
				((MainWindow) manager.getPanel(Constants.MAIN_VIEW_NAME)).getLateralPanel()
						.setLabels(manager.getGameManager().getUser(), false);
			break;
		case ("User Evo"):
			try {
				CashEvoExecutor ce = new CashEvoExecutor();
				ce.executaGrafic(manager, manager.getGameManager().getUser().getEmail(),
						manager.getGameManager().getUser().getName(), manager.getGameManager().getUser().getSurname());
				ce.setBack(false);
			} catch (IOException e2) {
				// e2.printStackTrace();
			}
			break;
		case ("Log Out"):
			if (!manager.getGameManager().isGuest()) {
				manager.logout();
				try {
					manager.getServer().enviarTrama(new LogOut(false));
				} catch (IOException e) {
					// e.printStackTrace();
				}
			}
			manager.lateralMainPanel(false);
			manager.showPanel("LoginWindow");
			break;
		case ("Home"):
			((CashEvoView) manager.getPanel(Constants.CASH_EVO_VIEW_NAME)).reset();
			manager.showPanel("MainWindow");

			if (guest)
				((MainWindow) manager.getPanel(Constants.MAIN_VIEW_NAME)).getLateralPanel()
						.setLabels(manager.getGameManager().getUser(), true);
			else
				((MainWindow) manager.getPanel(Constants.MAIN_VIEW_NAME)).getLateralPanel()
						.setLabels(manager.getGameManager().getUser(), false);
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
			((CashEvoView) manager.getPanel(Constants.CASH_EVO_VIEW_NAME)).reset();
			manager.showPanel(Constants.CASH_RANKING_VIEW_NAME);
			break;
		case ("Top 5 Roulette"):
			try {
				manager.getServer().enviarTrama(new Top5(null, 1));
				LinkedList<HistoricPartides> histRuleta = ((Top5) manager.getServer().obtenirTrama()).getHistoric();
				if (((Graphics) manager.getPanel(Constants.GRAPHICS_VIEW_NAME)).createChart(histRuleta)) {
					manager.showPanel(Constants.GRAPHICS_VIEW_NAME);
					manager.getGameManager().executaGrafics(true);
				}
			} catch (IOException e) {
				// e.printStackTrace();
			}
			break;
		case ("Top 5 Blackjack"):
			try {
				manager.getServer().enviarTrama(new Top5(null, 3));
				LinkedList<HistoricPartides> histBJ = ((Top5) manager.getServer().obtenirTrama()).getHistoric();
				if (((Graphics) manager.getPanel(Constants.GRAPHICS_VIEW_NAME)).createChart(histBJ)) {
					manager.showPanel(Constants.GRAPHICS_VIEW_NAME);
					manager.getGameManager().executaGrafics(true);
				}
			} catch (IOException e) {
				// e.printStackTrace();
			}
			break;
		case ("Top 5 Horses"):
			try {
				manager.getServer().enviarTrama(new Top5(null, 2));
				LinkedList<HistoricPartides> histCavalls = ((Top5) manager.getServer().obtenirTrama()).getHistoric();
				if (((Graphics) manager.getPanel(Constants.GRAPHICS_VIEW_NAME)).createChart(histCavalls)) {
					manager.showPanel(Constants.GRAPHICS_VIEW_NAME);
					manager.getGameManager().executaGrafics(true);
				}
			} catch (IOException e) {
				// e.printStackTrace();
			}
			break;
		case ("ALL BUTTON"):
			((CashEvoView) manager.getPanel(Constants.CASH_EVO_VIEW_NAME)).setLines(false, 0);
			((CashEvoView) manager.getPanel(Constants.CASH_EVO_VIEW_NAME)).setLines(false, 1);
			((CashEvoView) manager.getPanel(Constants.CASH_EVO_VIEW_NAME)).setLines(false, 2);
			((CashEvoView) manager.getPanel(Constants.CASH_EVO_VIEW_NAME)).setLines(false, 3);
			((CashEvoView) manager.getPanel(Constants.CASH_EVO_VIEW_NAME)).setLines(true, 4);
			break;
		case ("GLOBAL BUTTON"):
			((CashEvoView) manager.getPanel(Constants.CASH_EVO_VIEW_NAME)).setLines(false, 4);
			((CashEvoView) manager.getPanel(Constants.CASH_EVO_VIEW_NAME)).setLines(false, 1);
			((CashEvoView) manager.getPanel(Constants.CASH_EVO_VIEW_NAME)).setLines(false, 2);
			((CashEvoView) manager.getPanel(Constants.CASH_EVO_VIEW_NAME)).setLines(false, 3);
			((CashEvoView) manager.getPanel(Constants.CASH_EVO_VIEW_NAME)).setLines(true, 0);
			break;
		case ("ROULETTE BUTTON"):
			((CashEvoView) manager.getPanel(Constants.CASH_EVO_VIEW_NAME)).setLines(false, 4);
			((CashEvoView) manager.getPanel(Constants.CASH_EVO_VIEW_NAME)).setLines(false, 0);
			((CashEvoView) manager.getPanel(Constants.CASH_EVO_VIEW_NAME)).setLines(false, 2);
			((CashEvoView) manager.getPanel(Constants.CASH_EVO_VIEW_NAME)).setLines(false, 3);
			((CashEvoView) manager.getPanel(Constants.CASH_EVO_VIEW_NAME)).setLines(true, 1);
			break;
		case ("HORSES BUTTON"):
			((CashEvoView) manager.getPanel(Constants.CASH_EVO_VIEW_NAME)).setLines(false, 4);
			((CashEvoView) manager.getPanel(Constants.CASH_EVO_VIEW_NAME)).setLines(false, 0);
			((CashEvoView) manager.getPanel(Constants.CASH_EVO_VIEW_NAME)).setLines(false, 1);
			((CashEvoView) manager.getPanel(Constants.CASH_EVO_VIEW_NAME)).setLines(false, 3);
			((CashEvoView) manager.getPanel(Constants.CASH_EVO_VIEW_NAME)).setLines(true, 2);
			break;
		case ("BJ BUTTON"):
			((CashEvoView) manager.getPanel(Constants.CASH_EVO_VIEW_NAME)).setLines(false, 4);
			((CashEvoView) manager.getPanel(Constants.CASH_EVO_VIEW_NAME)).setLines(false, 0);
			((CashEvoView) manager.getPanel(Constants.CASH_EVO_VIEW_NAME)).setLines(false, 1);
			((CashEvoView) manager.getPanel(Constants.CASH_EVO_VIEW_NAME)).setLines(false, 2);
			((CashEvoView) manager.getPanel(Constants.CASH_EVO_VIEW_NAME)).setLines(true, 3);
			break;
		case ("Cash Ranking"):
			try {
				// Envia peticio
				manager.getServer().enviarTrama(new CashRanking(null));
				// Rep les dades
				ArrayList<Object[]> data = ((CashRanking) manager.getServer().obtenirTrama()).getData();
				// Envia el conjunt de dades complet (amb ID)
				manager.getRowListener().setData(data);
				// Crea l'array públic, les dades del qual es mostraran
				ArrayList<Object[]> publicData = new ArrayList<Object[]>();
				// Filtra les dades
				for (int i = 0; i < data.size(); i++) {
					Object[] row = new Object[3];
					System.arraycopy(data.get(i), 1, row, 0, 2);
					String cad = "";
					for (int j = 0; j < data.get(i)[3].toString().length() - 2; j++)
						cad = cad + "*";
					row[2] = new String(cad);
					publicData.add(row);
				}
				// Passa les dades públiques
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
			if (diners > initBJMoney)
				guanys = diners - initBJMoney;

			try {
				if (!guest){
					manager.getServer().enviarTrama(new BJEnd((float) guanys, (float) diners));
					manager.getServer().enviarTrama(new UserWanted(null));
					User u = ((UserWanted) manager.getServer().obtenirTrama()).getUser();
					u.setLoginInfo(manager.getGameManager().getUser().getLoginInfo());
					manager.getGameManager().setUser(u);
				}
			} catch (IOException e) {
				// e.printStackTrace();
			}

			if (guest)
				((MainWindow) manager.getPanel(Constants.MAIN_VIEW_NAME)).getLateralPanel()
						.setLabels(manager.getGameManager().getUser(), true);
			else
				((MainWindow) manager.getPanel(Constants.MAIN_VIEW_NAME)).getLateralPanel()
						.setLabels(manager.getGameManager().getUser(), false);

			break;
		case ("BET_R"):
			manager.getGameManager().sendRouletteBet();
			break;
		case ("BET_H"):
			((HorsesView) manager.getPanel(Constants.H_VIEW_NAME)).showPhv();
			break;
		case ("EXIT_H"):// Predict
			String cadCavalls = new Predictor(manager.getGameManager().getHorsesExecutor()).predictWinnerHorse();
			new Dialeg().setWarningText(cadCavalls);
			break;
		case ("EXIT_R"):// Predict
			String cadRuleta = new Predictor(manager.getGameManager().getRouletteExecutor()).predictWinnerNumber();
			new Dialeg().setWarningText(cadRuleta);
			break;
		case ("BET_BJ"):
			manager.getGameManager().betBJ(guest);
			break;
		case ("HIT_BJ"):
			manager.getGameManager().hitBJ();
			break;
		case ("STAND_BJ"):
			manager.getGameManager().standBJ();
			break;
		default:
			System.err.println(((JButton) event.getSource()).getToolTipText());
		}

	}// Tancament del metode

	/**
	 * Sets guest.
	 *
	 * @param guest
	 */
	public void setGuest(boolean guest) {
		this.guest = guest;
	}

	/**
	 * Gets pf.
	 *
	 * @return pf
	 */
	public PasswordFrame getPf() {
		return pf;
	}

	/**
	 * Gets af.
	 *
	 * @return af
	 */
	public AddMoneyFrame getAf() {
		return af;
	}

}// Tancament de la classe
