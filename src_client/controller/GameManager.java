package controller;

import java.io.IOException;
import java.util.Date;

import javax.swing.JOptionPane;

import controller.horses.HorsesManager;
import controller.roulette.EndingControl;
import controller.roulette.RouletteManager;
import model.LoginValidator;
import model.RegisterValidator;
import model.blackjack.Blackjack;
import model.struct.bet.HorsesBet;
import model.struct.user.User;
import network.segment.HorseBetting;
import view.Dialeg;
import view.blackjack.BlackjackView;
import view.roulette.RouletteView;

public class GameManager {
	private boolean apostaFeta;
	private User user;
	private Manager manager;
	private LoginValidator loginValidator;
	private Blackjack blackjack;
	private RouletteManager roulette;
	private RegisterValidator rv;
	private HorsesManager horses;
	private Thread fil;

	public GameManager(Manager manager) {
		this.manager = manager;
		loginValidator = new LoginValidator();
		rv = new RegisterValidator();
	}

	public boolean isGuest() {
		return (getUser().getName().equals("guest"));
	}

	public Boolean comprovaLoginPW(String pw) {
		return (loginValidator.validatePasswordFormat(pw));
	}

	public Boolean comprovaLoginMail(String email) {
		return (loginValidator.validateEmailFormat(email));
	}

	public void closeRuleta() {
		fil.interrupt();
	}

	public void executaRuleta(RouletteView rv) {
		roulette = new RouletteManager(manager);
		roulette.executaPartida(null);
		EndingControl gifControl = new EndingControl(manager, rv);
		fil = new Thread(gifControl);
		fil.start();
	}

	public void executaHorses() {
		horses = new HorsesManager(manager);
	}

	public void executaBlackjack() {
		blackjack = new Blackjack(user);
		resetBJTable();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public Boolean comprovaName(String name) {
		return (rv.validateName(name));
	}

	public Boolean comprovaSurname(String name) {
		return (rv.validateName(name));
	}

	public Boolean comprovaAge(Date date) {
		return (rv.validateAge(date));
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

	public void thisHorse() {
		if (horses.getIntro().getWindow().getAmount().isEmpty()
				|| Float.parseFloat(horses.getIntro().getWindow().getAmount()) <= 0) {
			Dialeg dialeg = new Dialeg();
			dialeg.setWarningText("You must enter a positive amount!");
		} else {
			horses.getIntro().getWindow().obreDialeg();

			if (horses.getIntro().getWindow().getDialeg().getResult() == JOptionPane.OK_OPTION) {
				String name = horses.getIntro().getWindow().getHorseName();
				HorsesBet bet = new HorsesBet(Float.parseFloat(horses.getIntro().getWindow().getAmount()), name);

				try {
					manager.getServer().enviarTrama(new HorseBetting(bet));
					apostaFeta = true;
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				horses.getIntro().getWindow().dispose();
				
			}
		}
	}

	public Blackjack getBlackjack() {
		return blackjack;
	}

	public RouletteManager getRoulette() {
		return roulette;
	}

	public HorsesManager getHorses() {
		return horses;
	}

	public boolean isApostaFeta() {
		return apostaFeta;
	}

	public void setApostaFeta(boolean apostaFeta) {
		this.apostaFeta = apostaFeta;
	}

	public void passaEsquerra() {
		horses.getIntro().getWindow().passaEsquerra();
	}

	public void passaDreta() {
		horses.getIntro().getWindow().passaDreta();
	}
}
