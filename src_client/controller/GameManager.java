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

package controller;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.JOptionPane;

import model.Bet;
import model.LoginValidator;
import model.RegisterValidator;
import model.blackjack.Blackjack;
import model.struct.horses.HorseData;
import model.struct.user.User;
import network.segment.Betting;
import network.segment.Check;
import network.segment.Segment;
import view.Dialeg;
import view.blackjack.BlackjackView;
import view.horses.HorsesView;
import view.roulette.MyButton;
import view.statistics.Graphics;

/**
 * The Class GameManager.
 * (Gestiona l'aspecte general del joc.)
 */
public class GameManager {

	// Atributs de la classe
	private User user;
	private Manager manager;
	private LoginValidator loginValidator;
	private Blackjack blackjack;
	private RegisterValidator rv;
	private HorsesExecutor horsesExecutor;
	private RouletteExecutor rouletteExecutor;
	private Thread filGif;
	private Bet apostaRuleta, horseBet;
	private Thread filGrafics;
	private MyButton boton;
	private AtomicBoolean bool;

	/**
	 * Instantiates a new game manager.
	 *
	 * @param manager
	 */
	public GameManager(Manager manager) {
		this.manager = manager;
		loginValidator = new LoginValidator();
		rv = new RegisterValidator();
		bool = new AtomicBoolean();
	}// Tancament del constructor

	/**
	 * Checks if user is guest.
	 *
	 * @return true, if is guest
	 */
	public boolean isGuest() {
		return (getUser().getName().equals("guest"));
	}// Tancament del metode

	// ---------------------------Roulette-------------------------------

	/**
	 * Gets roulette executor.
	 *
	 * @return roulette executor
	 */
	public RouletteExecutor getRouletteExecutor() {
		return rouletteExecutor;
	}

	/**
	 * (Executa la ruleta.)
	 */
	public void executaRoulette() {
		rouletteExecutor = new RouletteExecutor(manager);
		new Thread(rouletteExecutor).start();
	}// Tancament del metode

	/**
	 * (Gestiona les accions de quan cliques un nombre de la ruleta.)
	 *
	 * @param boton
	 */
	public void thisSlot(MyButton boton) {
		this.boton = boton;
		Dialeg dialeg = new Dialeg();
		dialeg.setInputText("How much money do you want to bet?");
		if (dialeg.getAmount() != null && (dialeg.getAmount().isEmpty()
				|| !dialeg.getAmount().matches("[-+]?\\d*\\.?\\d+") || Float.parseFloat(dialeg.getAmount()) <= 0)) {
			dialeg.setWarningText("Enter a correct amount!");
		} else if (dialeg.getAmount() != null) {
			String slot = boton.getText();
			Bet bet = new Bet(Double.parseDouble(dialeg.getAmount()), slot);
			rouletteExecutor.setAposta(bet);
			bool.set(true);
		}
	}// Tancament del metode

	/**
	 * (Tanca el fil del gif de la ruleta.)
	 */
	public void closeRuleta() {
		filGif.interrupt();
	}// Tancament del metode

	/**
	 * (Envia l'aposta de la ruleta.)
	 */
	public void sendRouletteBet() {
		if (rouletteExecutor.getAposta() == null)
			new Dialeg().setWarningText("You must bet!");
		else {
			if (bool.compareAndSet(true, false)) {
				try {
					manager.getServer().enviarTrama(new Betting(rouletteExecutor.getAposta()));
				} catch (IOException e) {
					//// e.printStackTrace();
				}
			} else {
				new Dialeg().setWarningText("You can't bet to the same number");
			}
		}
	}// Tancament del metode

	/**
	 * Gets boton.
	 *
	 * @return boton
	 */
	public MyButton getBoton() {
		return boton;
	}

	// ---------------------------Horses-------------------------------

	/**
	 * Gets horses executor.
	 *
	 * @return horses executor
	 */
	public HorsesExecutor getHorsesExecutor() {
		return horsesExecutor;
	}

	/**
	 * (Executa la cursa de cavalls.)
	 */
	public void executaHorses() {
		horsesExecutor = new HorsesExecutor(manager);
		new Thread(horsesExecutor).start();
	}// Tancament del metode

	/**
	 * (Quan l'usuari escull cavall, gestiona les accions fins a enviar l'aposta.)
	 */
	public void thisHorse() {
		HorsesView horses = (HorsesView) manager.getPanel(Constants.H_VIEW_NAME);
		if (horses.getPhv().getAmount().isEmpty() || !horses.getPhv().getAmount().matches("[-+]?\\d*\\.?\\d+")
				|| Float.parseFloat(horses.getPhv().getAmount()) <= 0) {
			new Dialeg().setWarningText("You must enter a positive amount!");
		} else {
			horses.getPhv().obreDialeg();
			if (horses.getPhv().getDialeg().getResult() == JOptionPane.OK_OPTION) {
				String name = horses.getPhv().getHorseName();
				horseBet = new Bet(Double.parseDouble(horses.getPhv().getAmount()), name);
				try {
					manager.getServer().enviarTrama(new Betting(horseBet));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				horses.getPhv().dispose();
			}
		}
	}// Tancament del metode

	/**
	 * Gets horses list.
	 *
	 * @return horses list
	 */
	public LinkedList<HorseData> getHorsesList() {
		return manager.getFileManager().getList();
	}// Tancament del getter

	// ---------------------------BlackJack-------------------------------

	/**
	 * (Executa el blackjack.)
	 */
	public void executaBlackjack() {
		blackjack = new Blackjack(user);
		resetBJTable();
	}// Tancament del metode

	/**
	 * (Gestiona l'aposta del BJ.)
	 *
	 * @param guest
	 *            the guest
	 */
	public void betBJ(boolean guest) {
		double bet = ((BlackjackView) manager.getPanel(Constants.BJ_VIEW_NAME)).getBet();
		if (!blackjack.isOkBet() && blackjack.canBet(bet)) {

			blackjack.setOkBet(true);
			if (guest) {
				blackjack.addBet(bet);
				startBJ();
				return;
			}

			Bet aposta = new Bet(bet, "blackJack");
			try {
				manager.getServer().enviarTrama(new Betting(aposta));
				Segment s = manager.getServer().obtenirTrama();
				if (!((Check) s).isOk())
					new Dialeg().setWarningText("Bet refused");
				else {
					new Dialeg().setWarningText("Bet accepted");
					blackjack.addBet(bet);
					((BlackjackView)manager.getPanel(Constants.BJ_VIEW_NAME)).updateCash(bet);
					startBJ();
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		} else {
			JOptionPane.showMessageDialog((BlackjackView) manager.getPanel(Constants.BJ_VIEW_NAME),
					"There has been an error with the bet:\n The minimum bet is 10, or\n You have not enough funds",
					"ERROR", JOptionPane.PLAIN_MESSAGE);
		}
	}// Tancament del metode

	/**
	 * (Comença la partida BJ.)
	 */
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
	}// Tancament del metode

	/**
	 * (Neteja la taula de BJ.)
	 */
	public void resetBJTable() {
		((BlackjackView) manager.getPanel(Constants.BJ_VIEW_NAME)).clearTable(blackjack.getCashAmount());
		if (blackjack.getCashAmount() <= 0) {
			JOptionPane.showMessageDialog(((BlackjackView) manager.getPanel(Constants.BJ_VIEW_NAME)),
					"You ran out of cash! Please, go back\n to the menu and make a deposit to\n keep playing.",
					"** NO MONEY **", JOptionPane.PLAIN_MESSAGE);
		}
		blackjack.setOkBet(false);
	}// Tancament del metode

	/**
	 * (Afegeix una carta del BJ.)
	 */
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
	}// Tancament del metode

	/**
	 * (Es planta en la partida de BJ.)
	 */
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
	}// Tancament del metode

	/**
	 * Gets blackjack.
	 *
	 * @return blackjack
	 */
	public Blackjack getBlackjack() {
		return blackjack;
	}// Tancament del getter

	// ---------------------------Other-------------------------------

	/**
	 * (Comprova que un nom sigui correcte.)
	 *
	 * @param name
	 * @return boolean
	 */
	public Boolean comprovaName(String name) {
		return (rv.validateName(name));
	}// Tancament del metode

	/**
	 * (Comprova que un cognom sigui correcte.)
	 *
	 * @param name
	 * @return boolean
	 */
	public Boolean comprovaSurname(String name) {
		return (rv.validateName(name));
	}// Tancament del metode

	/**
	 * (Comprova que una data sigui correcte, tot i ja tenir el DatePicker.)
	 *
	 * @param date
	 * @return boolean
	 */
	public Boolean comprovaAge(Date date) {
		return (rv.validateAge(date));
	}// Tancament del metode

	/**
	 * (Comprova que una contrasenya sigui correcta.)
	 *
	 * @param pw
	 * @return boolean
	 */
	public Boolean comprovaLoginPW(String pw) {
		return (loginValidator.validatePasswordFormat(pw));
	}// Tancament del metode

	/**
	 * (Comprova que un correu electrònic sigui correcte.)
	 *
	 * @param email
	 * @return boolean
	 */
	public Boolean comprovaLoginMail(String email) {
		return (loginValidator.validateEmailFormat(email));
	}// Tancament del metode

	/**
	 * Gets user.
	 *
	 * @return user
	 */
	public User getUser() {
		return user;
	}// Tancament del getter

	/**
	 * Sets user.
	 *
	 * @param user
	 */
	public void setUser(User user) {
		this.user = user;
	}// Tancament del setter

	/**
	 * Gets aposta ruleta.
	 *
	 * @return aposta ruleta
	 */
	public Bet getApostaRuleta() {
		return apostaRuleta;
	}// Tancament del getter

	/**
	 * Gets aposta cavalls.
	 *
	 * @return aposta cavalls
	 */
	public Bet getApostaCavalls() {
		return horseBet;
	}

	/**
	 * (Executa gràfics.)
	 *
	 * @param pinta
	 *            the pinta
	 */
	public void executaGrafics(boolean pinta) {
		if (pinta) {
			filGrafics = new Thread(((Graphics) manager.getPanel(Constants.GRAPHICS_VIEW_NAME)).getChart());
			filGrafics.start();
		} else {
			if (filGrafics.isAlive()) {
				filGrafics.interrupt();
				((Graphics) manager.getPanel(Constants.GRAPHICS_VIEW_NAME)).getChart().stop();
			}

		}
	}// Tancament del metode

}// Tancament de la classe
