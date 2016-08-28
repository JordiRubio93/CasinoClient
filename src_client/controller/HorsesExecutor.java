
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
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import model.Calcul;
import model.struct.horses.HorseData;
import model.struct.user.User;
import network.segment.Check;
import network.segment.GameOver;
import network.segment.InitHorses;
import network.segment.NotifyBet;
import network.segment.Seconds;
import network.segment.Segment;
import network.segment.UserWanted;
import view.Dialeg;
import view.GameView;
import view.MainWindow;
import view.horses.HorsesView;

/**
 * The Class HorsesExecutor.
 * (Fil d'execució que s'encarrega de la cursa de cavalls.)
 */
public class HorsesExecutor implements Runnable {
	// Atributs de la classe
	private boolean active;
	private Segment s;
	private HorsesView game;
	private int seconds;
	private Manager manager;
	private int sec;
	private String horse;

	/**
	 * Instantiates a new horses executor.
	 *
	 * @param manager
	 */
	public HorsesExecutor(Manager manager) {
		this.manager = manager;
		active = true;
		game = (HorsesView) manager.getPanel(Constants.H_VIEW_NAME);
		manager.getMouseListener().setWindow(game.getPhv());
	}// Tancament del constructor

	/**
	 * (S'encarrega de rebre trames del servidor i proseguir amb el funcionament de cadascuna:
	 * - Check envia l'estat de l'aposta.
	 * - NotifyBet és per afegir una aposta al panell lateral.
	 * - Seconds és per rebre els segons de joc en els quals es troba el servidor.
	 * - InitHorses és per començar la cursa de cavalls i tota la seva execució.)
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
						((GameView) manager.getPanel(Constants.H_VIEW_NAME))
								.actualitzaLabelApostaPropia(manager.getGameManager().getApostaCavalls().getSlot());
						((GameView) manager.getPanel(Constants.H_VIEW_NAME)).disableBet();
					}
					break;
				case "NotifyBet":
					NotifyBet aposta = ((NotifyBet) s);
					game.addAtList(aposta.getPublicUser(), aposta.getAposta());
					break;
				case "InitHorses":
					((GameView) manager.getPanel(Constants.H_VIEW_NAME)).disableBet();
					game.setCursa();
					InitHorses ih = ((InitHorses) s);
					horse = ih.getDades().get(getWinner(ih.getDades())).getName();
					game.initHorses(ih.getDades());
					corre(ih.getDades());
					manager.getServer().enviarTrama(new GameOver(2));
					String winner = "The winner horse is... " + horse.toUpperCase() + " !";
					Dialeg d = new Dialeg();
					d.setWarningText(winner + "\nThanks for playing!");
					game.reset();
					ih.getDades().clear();
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
						game.setCounter();
						game.setActual(sec);

						new Timer().scheduleAtFixedRate(new TimerTask() {
							public void run() {
								if (sec < 45) {
									sec++;
									game.actualitzaCounter(45 - sec);
									game.actualitzaProgressBar(sec);
								} else {
									game.showCounter(false);
									this.cancel();
								}

								if ((45 - sec) == 1 || (45 - sec) == 3 || (45 - sec) == 5)
									game.paintRed(true);
								else if ((45 - sec) < 5)
									game.paintRed(false);
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
	 * (Fa que els cavalls corrin per la pantalla).
	 *
	 * @param end
	 */
	private void corre(LinkedList<HorseData> end) {
		seconds = 0;
		while (seconds < 30) {
			seconds++;
			for (int i = 0; i < Constants.nHorses; i++) {
				if (seconds % 2 == 0)
					game.runHorses(i, Calcul.calculaX(end.get(i).getSegons(), true), Calcul.calculaY(i));
				else
					game.runHorses(i, Calcul.calculaX(end.get(i).getSegons(), false), Calcul.calculaY(i));
			}
			try {
				Thread.sleep(Constants.DELAY);
			} catch (InterruptedException e) {
				// e.printStackTrace();
			}
		}
		// get.enviarTrama(new GameOver());
	}// Tancament del metode

	/**
	 * Gets winner.
	 *
	 * @param ih
	 * @return winner
	 */
	public int getWinner(LinkedList<HorseData> ih) {
		int win = 0;
		int min = Integer.MIN_VALUE;
		for (int i = 0; i < ih.size(); i++) {
			if (ih.get(i).getSegons() > min) {
				min = ih.get(i).getSegons();
				win = i;
			}
		}
		return win;
	}

	/**
	 * Gets horse.
	 *
	 * @return horse
	 */
	public String getHorse() {
		return horse;
	}

	/**
	 * Gets seconds.
	 *
	 * @return seconds
	 */
	public int getSeconds() {
		return seconds;
	}// Tancament del Getter

	/**
	 * Sets active.
	 *
	 * @param active
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

}// Tancament de la classe
