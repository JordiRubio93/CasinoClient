package controller;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import model.Bet;
import model.LoginValidator;
import model.RegisterValidator;
import model.blackjack.Blackjack;
import model.struct.horses.HorseData;
import model.struct.user.User;
import network.segment.Betting;
import view.Dialeg;
import view.blackjack.BlackjackView;
import view.cavalls.HorsesView;
import view.roulette.MyButton;
import view.roulette.RouletteView;
import view.statistics.Graphics;

public class GameManager {
	private User user;
	private Manager manager;
	private LoginValidator loginValidator;
	private Blackjack blackjack;
	private RegisterValidator rv;
	private HorsesExecutor horsesExecutor;
	private RouletteExecutor rouletteExecutor;
	private Thread filGif;
	private Bet apostaRuleta;
	private Thread filGrafics;

	public GameManager(Manager manager) {
		this.manager = manager;
		loginValidator = new LoginValidator();
		rv = new RegisterValidator();
	}

	public boolean isGuest() {
		return (getUser().getName().equals("guest"));
	}

	//---------------------------Roulette-------------------------------

	public void executaRoulette() {
		System.out.println("executan el thread roulette");
		rouletteExecutor = new RouletteExecutor(manager.getServer().getObjectIn(), manager.getServer().getObjectOut(), manager);
		new Thread(rouletteExecutor).start();
	}
	
	public void thisSlot(MyButton boton) {
		Dialeg dialeg = new Dialeg();
		dialeg.setInputText("How much money you want to bet?");
		if (dialeg.getAmount() != null && (dialeg.getAmount().isEmpty() || Float.parseFloat(dialeg.getAmount()) <= 0)) {
			dialeg.setWarningText("Enter a correct amount!");
			((RouletteView) manager.getPanel(Constants.R_VIEW_NAME)).pintaBoto(boton);
		} else if (dialeg.getAmount() != null) {
			String slot = boton.getText();
			Bet bet = new Bet(Double.parseDouble(dialeg.getAmount()), slot);
			System.out.println(rouletteExecutor.toString());
			
			rouletteExecutor.setAposta(bet);
		}
	}
		
	public void closeRuleta() {
		filGif.interrupt();
	}

	public void sendRouletteBet() {
		if (rouletteExecutor.getAposta() == null) new Dialeg().setWarningText("You must bet!");
		else{
			try {
				manager.getServer().enviarTrama(new Betting(rouletteExecutor.getAposta()));
			} catch (IOException e) {
				e.printStackTrace();
			} 					
		}
	}
			
	
	//---------------------------Horses-------------------------------

	
	
	public void executaHorses() {
		horsesExecutor = new HorsesExecutor(manager.getServer().getObjectIn(), manager.getServer().getObjectOut(), manager);
		new Thread(horsesExecutor).start();
	}

	public void thisHorse(){
		HorsesView horses = (HorsesView) manager.getPanel(Constants.H_VIEW_NAME);
		if (horses.getPhv().getAmount().isEmpty()
				|| Float.parseFloat(horses.getPhv().getAmount()) <= 0) {
			 new Dialeg().setWarningText("You must enter a positive amount!");
		} else {
			horses.getPhv().obreDialeg();

			if (horses.getPhv().getDialeg().getResult() == JOptionPane.OK_OPTION) {
				String name = horses.getPhv().getHorseName();
				Bet bet = new Bet(Double.parseDouble(horses.getPhv().getAmount()), name);
				try {
					manager.getServer().enviarTrama(new Betting(bet));					
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				horses.getPhv().dispose();
			}
		}
	}

	public LinkedList<HorseData> getHorsesList() {
		return manager.getFileManager().getList();
	}
	
	//---------------------------BlackJack-------------------------------

	public void executaBlackjack() {
		blackjack = new Blackjack(user);
		resetBJTable();
	}
	
	public void betBJ() {
		double bet = ((BlackjackView) manager.getPanel(Constants.BJ_VIEW_NAME)).getBet();
		if (!blackjack.isOkBet() && blackjack.canBet(bet)) {
			blackjack.setOkBet(true);
			blackjack.addBet(bet);
			startBJ();
		} else {
			JOptionPane.showMessageDialog((BlackjackView) manager.getPanel(Constants.BJ_VIEW_NAME),
					"There has been an error with the bet:\n The minimum bet is 10, or\n You have not enough funds",
					"ERROR", JOptionPane.PLAIN_MESSAGE);
		}
	}
	
	public void startBJ() {
		BlackjackView blackjackview = ((BlackjackView) manager.getPanel(Constants.BJ_VIEW_NAME));
		blackjack.newGame();

		blackjackview.addCard(blackjack.giveCard(1), 1);
		blackjackview.addCard(blackjack.giveCard(1), 1);
		blackjackview.dealerCards();
		blackjackview.dealerCards();

		if (blackjack.getCardCount(1) == 21) {
			JOptionPane.showMessageDialog(blackjackview, "You got Blackjack!!! Congratulations!!!", "** BLACKJACK **",
					JOptionPane.PLAIN_MESSAGE);
			blackjack.playerBlackjack();
			resetBJTable();
		}
	}

	public void resetBJTable() {
		((BlackjackView) manager.getPanel(Constants.BJ_VIEW_NAME)).clearTable(blackjack.getCashAmount());
		if (blackjack.getCashAmount() <= 0) {
			JOptionPane.showMessageDialog(((BlackjackView) manager.getPanel(Constants.BJ_VIEW_NAME)),
					"You ran out of cash! Please, go back\n to the menu and make a deposit to\n keep playing.",
					"** NO MONEY **", JOptionPane.PLAIN_MESSAGE);
		}
		blackjack.setOkBet(false);
	}

	public void hitBJ() {
		if (blackjack.isOkBet()) {
			if (blackjack.getCount(1, false, 21)) {
				((BlackjackView) manager.getPanel(Constants.BJ_VIEW_NAME)).addCard(blackjack.giveCard(1), 1);
				if (blackjack.getCount(1, true, 21)) {
					blackjack.playerLoses();
					JOptionPane.showMessageDialog(manager.getPanel(Constants.BJ_VIEW_NAME), "You lose!", "BUSTED",
							JOptionPane.PLAIN_MESSAGE);
					resetBJTable();
				}
			}
		} else
			JOptionPane.showMessageDialog(manager.getPanel(Constants.BJ_VIEW_NAME), "You must bet something", "ERROR",
					JOptionPane.PLAIN_MESSAGE);
	}

	public void standBJ() {
		if (blackjack.isOkBet()) {
			((BlackjackView) manager.getPanel(Constants.BJ_VIEW_NAME)).standAction();

			while (blackjack.getCount(2, false, 17)) {
				((BlackjackView) manager.getPanel(Constants.BJ_VIEW_NAME)).addCard(blackjack.giveCard(2), 2);
			}

			if (blackjack.getCount(2, true, 21)) {
				JOptionPane.showMessageDialog(((BlackjackView) manager.getPanel(Constants.BJ_VIEW_NAME)), "You win!",
						"DEALER BUSTS", JOptionPane.PLAIN_MESSAGE);
				blackjack.stand(true);
			} else {
				if (blackjack.getCount(1, true, blackjack.getCardCount(2))) {
					JOptionPane.showMessageDialog(((BlackjackView) manager.getPanel(Constants.BJ_VIEW_NAME)),
							"You win!", "YOU WIN", JOptionPane.PLAIN_MESSAGE);
					blackjack.stand(true);
				} else if (blackjack.getCount(1, false, blackjack.getCardCount(2))) {
					JOptionPane.showMessageDialog(((BlackjackView) manager.getPanel(Constants.BJ_VIEW_NAME)),
							"You lose!", "YOU LOSE", JOptionPane.PLAIN_MESSAGE);
					blackjack.stand(false);
				} else {
					JOptionPane.showMessageDialog(((BlackjackView) manager.getPanel(Constants.BJ_VIEW_NAME)),
							"You push.", "PUSH", JOptionPane.PLAIN_MESSAGE);
				}
			}

			resetBJTable();
		} else
			JOptionPane.showMessageDialog(manager.getPanel(Constants.BJ_VIEW_NAME), "You must bet something", "ERROR",
					JOptionPane.PLAIN_MESSAGE);
	}

	public Blackjack getBlackjack() {
		return blackjack;
	}


	
	//---------------------------Other-------------------------------
	
	public Boolean comprovaName(String name) {
		return (rv.validateName(name));
	}

	public Boolean comprovaSurname(String name) {
		return (rv.validateName(name));
	}

	public Boolean comprovaAge(Date date) {
		return (rv.validateAge(date));
	}
	
	public Boolean comprovaLoginPW(String pw) {
		return (loginValidator.validatePasswordFormat(pw));
	}

	public Boolean comprovaLoginMail(String email) {
		return (loginValidator.validateEmailFormat(email));
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Bet getApostaRuleta() {
		return apostaRuleta;
	}

	public void executaGrafics(boolean pinta){
		if(pinta){
			filGrafics = new Thread(((Graphics) manager.getPanel(Constants.GRAPHICS_VIEW_NAME)).getChart());
			filGrafics.start();
		}else{
			if(filGrafics.isAlive()){
				filGrafics.interrupt();
				((Graphics) manager.getPanel(Constants.GRAPHICS_VIEW_NAME)).getChart().stop();
			}
			
		}
	}
}
