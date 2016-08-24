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
import view.cavalls.HorsesView;
import view.roulette.MyButton;
import view.statistics.Graphics;

/**
 * 
 * <p>
 * <b> Classe: GameManager </b> <br/>
 * </p>
 * 
 * @version 1.0 19/05/2016
 * @author  Pol Valés - ls30599@salleurl.edu <br/>
 * 			Diego Bellino - ls30741@salleurl.edu <br/>
 * 			Enric Marin - ls31308@salleurl.edu <br/>
 * 			Jordi Rubió - ls31289@salleurl.edu <br/>
 * 			David Estepa - ls30622@salleurl.edu <br/>
 * 			Disseny i programació orientats a objectes. <br/>
 * 			La Salle - Universitat Ramon Llull. <br/>
 * 
 */
public class GameManager {
	
	//Atributs de la classe
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
	 * Constructor per la grafica de la ruleta.
	 */
	public GameManager(Manager manager) {
		this.manager = manager;
		loginValidator = new LoginValidator();
		rv = new RegisterValidator();
		bool = new AtomicBoolean();
	}//Tancament del constructor

	/**
	 * Metode que retorna un boolean indicant si l'usuari es usuari convidat o no.
	 * @return (getUser().getName().equals("guest")).
	 */
	public boolean isGuest() {
		return (getUser().getName().equals("guest"));
	}//Tancament del metode

	//---------------------------Roulette-------------------------------

	public RouletteExecutor getRouletteExecutor(){
		return rouletteExecutor;
	}
	
	/**
	 * Metode encarregat d'executar la ruleta.
	 */
	public void executaRoulette() {
		rouletteExecutor = new RouletteExecutor(manager);
		new Thread(rouletteExecutor).start();
	}//Tancament del metode
	
	/**
	 * Metode que no retorna res, rep un MyButton i s'encarrega de tractar l'aposta.
	 * @param boton (boto al qual s'ha clicat per apostar)
	 */
	public void thisSlot(MyButton boton) {
		this.boton = boton;
		Dialeg dialeg = new Dialeg();
		dialeg.setInputText("How much money do you want to bet?");
		if (dialeg.getAmount() != null && (dialeg.getAmount().isEmpty() 
				|| !dialeg.getAmount().matches("[-+]?\\d*\\.?\\d+")
				|| Float.parseFloat(dialeg.getAmount()) <= 0)) {
			dialeg.setWarningText("Enter a correct amount!");
		} else if (dialeg.getAmount() != null) {
			String slot = boton.getText();
			Bet bet = new Bet(Double.parseDouble(dialeg.getAmount()), slot);
			rouletteExecutor.setAposta(bet);
			bool.set(true);
		}
	}//Tancament del metode
	
	/**
	 * Metode que no retorna res i que s'encarrega de tancar el gif de la ruleta.
	 */
	public void closeRuleta() {
		filGif.interrupt();
	}//Tancament del metode

	/**
	 * Metode que no retorna res i que s'encarrega d'enviar l'aposta que s'ha fet.
	 */
	public void sendRouletteBet() {
		if (rouletteExecutor.getAposta() == null) new Dialeg().setWarningText("You must bet!");
		else{
			if(bool.compareAndSet(true, false)){
				try {
					manager.getServer().enviarTrama(new Betting(rouletteExecutor.getAposta()));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else{
				new Dialeg().setWarningText("You can't bet to the same number");
			}
		}
	}//Tancament del metode
	
	public MyButton getBoton(){
		return boton;
	}
	
	//---------------------------Horses-------------------------------

	public HorsesExecutor getHorsesExecutor(){
		return horsesExecutor;
	}
	
	/**
	 * Metode encarregat d'executar la cursa de cavalls.
	 */
	public void executaHorses() {
		horsesExecutor = new HorsesExecutor(manager);
		new Thread(horsesExecutor).start();
	}//Tancament del metode

	/**
	 * Metode que s'encarrega de tractar l'aposta al cavall que es vol.
	 */
	public void thisHorse(){
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
	}//Tancament del metode

	/**
	 * Getter de HorsesList.
	 */
	public LinkedList<HorseData> getHorsesList() {
		return manager.getFileManager().getList();
	}//Tancament del getter
	
	//---------------------------BlackJack-------------------------------

	/**
	 * Metode encarregat d'executar el blackjack.
	 */
	public void executaBlackjack() {
		blackjack = new Blackjack(user);
		resetBJTable();
	}//Tancament del metode
	
	/**
	 * Metode que no retorna res, rep un boolean i s'encarrega de tractar les apostes del blackjack.
	 * @param guest (Boolean que indica si l'usuari es o no un usuari convidat)
	 */
	public void betBJ(boolean guest) {
		double bet = ((BlackjackView) manager.getPanel(Constants.BJ_VIEW_NAME)).getBet();
		if (!blackjack.isOkBet() && blackjack.canBet(bet)) {
			
			blackjack.setOkBet(true);
			if(guest){
				blackjack.addBet(bet);
				startBJ();
				return;
			}
			
			Bet aposta = new Bet(bet, "blackJack");
			try {
				manager.getServer().enviarTrama(new Betting(aposta));	
				Segment s = manager.getServer().obtenirTrama();
				if(!((Check) s).isOk()) new Dialeg().setWarningText("Bet refused");
				else{ 
					new Dialeg().setWarningText("Bet accepted");			
					blackjack.addBet(bet);
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
	}//Tancament del metode
	
	/**
	 * Metode que no retorna res i que s'encarrega de fer començar el blackjack.
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
	}//Tancament del metode

	/**
	 * Metode que no retorna res i que s'encarrega de resetejar el blackjack.
	 */
	public void resetBJTable() {
		((BlackjackView) manager.getPanel(Constants.BJ_VIEW_NAME)).clearTable(blackjack.getCashAmount());
		if (blackjack.getCashAmount() <= 0) {
			JOptionPane.showMessageDialog(((BlackjackView) manager.getPanel(Constants.BJ_VIEW_NAME)),
					"You ran out of cash! Please, go back\n to the menu and make a deposit to\n keep playing.",
					"** NO MONEY **", JOptionPane.PLAIN_MESSAGE);
		}
		blackjack.setOkBet(false);
	}//Tancament del metode
	
	/**
	 * Metode que no retorna res i que s'encarrega de tractar els hits al blackjack.
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
	}//Tancament del metode

	/**
	 * Metode que no retorna res i que s'encarrega de tractar els stands al blackjack.
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
	}//Tancament del metode

	/**
	 * Getter de Blackjack.
	 */
	public Blackjack getBlackjack() {
		return blackjack;
	}//Tancament del getter


	
	//---------------------------Other-------------------------------
	
	/**
	 * Metode que retorna un boolean, rep un String i que s'encarrega de comprovar el nom de l'usuari.
	 * @return (rv.validateName(name))
	 * @param name (nom a comprovar)
	 */
	public Boolean comprovaName(String name) {
		return (rv.validateName(name));
	}//Tancament del metode

	/**
	 * Metode que retorna un boolean, rep un String i que s'encarrega de comprovar el cognom de l'usuari.
	 * @return (rv.validateName(name))
	 * @param name (nom a comprovar)
	 */
	public Boolean comprovaSurname(String name) {
		return (rv.validateName(name));
	}//Tancament del metode

	/**
	 * Metode que retorna un boolean, rep un String i que s'encarrega de comprovar l'edat de l'usuari.
	 * @return (rv.validateAge(date))
	 * @param date (data a comprovar)
	 */
	public Boolean comprovaAge(Date date) {
		return (rv.validateAge(date));
	}//Tancament del metode
	
	/**
	 * Metode que retorna un boolean, rep un String i que s'encarrega de comprovar el password de l'usuari.
	 * @return (loginValidator.validatePasswordFormat(pw))
	 * @param pw (password a comprovar)
	 */
	public Boolean comprovaLoginPW(String pw) {
		return (loginValidator.validatePasswordFormat(pw));
	}//Tancament del metode

	/**
	 * Metode que retorna un boolean, rep un String i que s'encarrega de comprovar el mail de l'usuari.
	 * @return (loginValidator.validateEmailFormat(email))
	 * @param email (mail a comprovar)
	 */
	public Boolean comprovaLoginMail(String email) {
		return (loginValidator.validateEmailFormat(email));
	}//Tancament del metode
	
	/**
	 * Getter de User.
	 */
	public User getUser() {
		return user;
	}//Tancament del getter

	/**
	 * Setter de User.
	 */
	public void setUser(User user) {
		this.user = user;
	}//Tancament del setter

	public Bet getApostaRuleta() {
		return apostaRuleta;
	}//Tancament del getter
	
	public Bet getApostaCavalls(){
		return horseBet;
	}

	/**
	 * Metode que no retorna res, rep un Boolean i s'encarrega d'executar els grafics.
	 * @param pinta (Boolean que indica si volem pintar o no)
	 */
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
	}//Tancament del metode
	
}//Tancament de la classe
