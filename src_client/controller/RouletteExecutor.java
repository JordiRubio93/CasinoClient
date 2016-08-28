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

import java.awt.Color;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import model.AmericanRoulette;
import model.Bet;
import model.struct.user.User;
import network.segment.Check;
import network.segment.GameOver;
import network.segment.InitRoulette;
import network.segment.NotifyBet;
import network.segment.Seconds;
import network.segment.Segment;
import network.segment.UserWanted;
import view.Dialeg;
import view.GameView;
import view.MainWindow;
import view.roulette.RouletteView;

/**
 * The Class RouletteExecutor.
 * (Fil d'execució que s'encarrega del joc de la ruleta.)
 */
public class RouletteExecutor implements Runnable {
	// Atributs de la classe
	private boolean active;
	private Segment s;
	private RouletteView game;
	private Bet aposta;
	private Manager manager;
	private int sec;
	private String number;

	/**
	 * Instantiates a new roulette executor.
	 *
	 * @param manager
	 */
	public RouletteExecutor(Manager manager) {
		this.manager = manager;
		active = true;
		game = (RouletteView) manager.getPanel(Constants.R_VIEW_NAME);
	}// Tancament del constructor

	/**
	 * (S'encarrega de rebre trames del servidor i proseguir amb el funcionament de cadascuna:
	 * - Check envia l'estat de l'aposta.
	 * - NotifyBet és per afegir una aposta al panell lateral.
	 * - Seconds és per rebre els segons de joc en els quals es troba el servidor.
	 * - InitRoulette és per començar el joc de la ruleta i tota la seva execució.)
	 */
	@Override
	public synchronized void run() {
		try {
			while (active) {
				switch (obtenirInstruccio().getClass().getSimpleName()) {
				case "Check":
					if (!((Check) s).isOk())
						new Dialeg().setWarningText("Bet refused");
					else {
						new Dialeg().setWarningText("Bet accepted");
						((GameView) manager.getPanel(Constants.R_VIEW_NAME))
								.actualitzaLabelApostaPropia(aposta.getSlot());
						((RouletteView) manager.getPanel(Constants.R_VIEW_NAME))
								.pintaBoto(manager.getGameManager().getBoton());
					}
					break;
				case "NotifyBet":
					NotifyBet aposta = ((NotifyBet) s);
					game.addAtList(aposta.getPublicUser(), aposta.getAposta());
					break;
				case "InitRoulette":
					((GameView) manager.getPanel(Constants.R_VIEW_NAME)).disableBet();
					InitRoulette resultat = ((InitRoulette) s);
					number = resultat.getWinner();
					mostragif();
					manager.getServer().enviarTrama(new GameOver(1));
					String winner = "The winner number is... " + number + " !";
					Dialeg d = new Dialeg();
					d.setWarningText(winner + "\nThanks for playing!", trobaColor(number));
					game.reset();
					manager.showPanel(Constants.MAIN_VIEW_NAME);
					manager.getServer().enviarTrama(new UserWanted(null));
					User u = ((UserWanted) manager.getServer().obtenirTrama()).getUser();
					u.setLoginInfo(manager.getGameManager().getUser().getLoginInfo());
					manager.getGameManager().setUser(u);
					if (manager.getGameManager().isGuest())
						((MainWindow) manager.getPanel(Constants.MAIN_VIEW_NAME)).getLateralPanel()
								.setLabels(manager.getGameManager().getUser(), true);
					else
						((MainWindow) manager.getPanel(Constants.MAIN_VIEW_NAME)).getLateralPanel()
								.setLabels(manager.getGameManager().getUser(), false);

					return;
				case "Seconds":
					sec = ((Seconds) s).getSegons();
					if (sec <= 45 && sec >= 0) {
						game.setActual(sec);
						new Timer().scheduleAtFixedRate(new TimerTask() {
							public void run() {
								if (sec < 45) {
									sec++;
									game.actualitzaProgressBar(sec);
								} else {
									this.cancel();
								}
							}
						}, 0, 1000);
					}
					break;
				
				}
			}
		} catch (NullPointerException | ClassNotFoundException | IOException e) {
			return;
		}
	}

	/**
	 * Gets aposta.
	 *
	 * @return aposta
	 */
	public Bet getAposta() {
		return aposta;
	}

	/**
	 * Sets aposta.
	 *
	 * @param aposta
	 */
	public void setAposta(Bet aposta) {
		this.aposta = aposta;
	}

	/**
	 * (Obté la instrucció del servidor.)
	 *
	 * @return segment
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public Segment obtenirInstruccio() throws ClassNotFoundException, IOException {
		s = (Segment) manager.getServer().obtenirTrama();
		if (s != null) {
		} // System.out.println(Calendar.getInstance().getTime().toString() + "
			// soc un " + s.getClass());
		return s;
	}

	/**
	 * (Mostra el gif de la ruleta.)
	 */
	private void mostragif() {
		game.insereixGif();
	}// Tancament del metode

	/**
	 * Sets active.
	 *
	 * @param activeç
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * Checks if is active.
	 *
	 * @return true, if is active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * Gets number.
	 *
	 * @return number
	 */
	public String getNumber() {
		return number;
	}
	
	/**
	 * (Troba el color d'una casella.)
	 * 
	 * @param num
	 * @return color
	 */
	public Color trobaColor(String num){
		return AmericanRoulette.getColorCasella(Integer.parseInt(num));
	}

}// Tancament de la classe
