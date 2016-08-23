package controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import model.Bet;
import network.segment.Check;
import network.segment.GameOver;
import network.segment.InitRoulette;
import network.segment.NotifyBet;
import network.segment.Seconds;
import network.segment.Segment;
import view.Dialeg;
import view.GameView;
import view.MainWindow;
import view.roulette.RouletteView;

/**
 * 
 * <p>
 * <b> Classe: RouletteExecutor </b> <br/>
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
public class RouletteExecutor implements Runnable {
	//Atributs de la classe
	private boolean active;
	private Segment s;
	private RouletteView game;
	private Bet aposta;
	private Manager manager;
	private int sec;
	private String number;
	
	/**
	 * Constructor del RouletteExecutor.
	 */
	public RouletteExecutor(Manager manager) {
		this.manager = manager;
		active = true;
		game = (RouletteView) manager.getPanel(Constants.R_VIEW_NAME);
	}//Tancament del constructor

	@Override
	public synchronized void run() {
		try {
			while (active) {
				switch (obtenirInstruccio().getClass().getSimpleName()) {
				case "Check":
					if(!((Check) s).isOk()) new Dialeg().setWarningText("Bet refused");
					else{
						new Dialeg().setWarningText("Bet accepted");
						((GameView) manager.getPanel(Constants.R_VIEW_NAME)).actualitzaLabelApostaPropia(aposta.getSlot());
						((RouletteView) manager.getPanel(Constants.R_VIEW_NAME)).pintaBoto(manager.getGameManager().getBoton());
					}
					break;
				case "NotifyBet":
					NotifyBet aposta = ((NotifyBet) s);
					System.err.println("Agreguem al panell lateral " + aposta.getPublicUser().getSurname()
							+ " ha apostat" + aposta.getAposta().getAmount() + " al numero " + aposta.getAposta().getAmount());			
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
					d.setWarningText(winner +"\nThanks for playing!");
					game.reset();
					manager.showPanel(Constants.MAIN_VIEW_NAME);
					manager.getGameManager().getUser().setCash(manager.getGameManager().getUser().getCash() + resultat.getGuanys());
					
					if(manager.getGameManager().isGuest())
						((MainWindow)manager.getPanel(Constants.MAIN_VIEW_NAME)).getLateralPanel().setLabels(manager.getGameManager().getUser(), true);
					else
						((MainWindow)manager.getPanel(Constants.MAIN_VIEW_NAME)).getLateralPanel().setLabels(manager.getGameManager().getUser(), false);
					
					return;
				case "Seconds":
					sec = ((Seconds) s).getSegons();
					if(sec <= 45 && sec >= 0){	
						game.setActual(sec);
						new Timer().scheduleAtFixedRate(new TimerTask(){
							public void run() {
								if(sec < 45){
									sec++;
									game.actualitzaProgressBar(sec);
								}else{
									this.cancel();
								}
							}
						}, 0, 1000);
					}
					break;
				default:
					System.err.println("no se que m'ha arribat");
					break;
				}
			}
		} catch (NullPointerException | ClassNotFoundException | IOException e) {
			return;
		}
	}
	
	/**
	 * Getter de Aposta.
	 */
	public Bet getAposta() {
		return aposta;
	}

	/**
	 * Setter de Aposta.
	 */
	public void setAposta(Bet aposta) {
		this.aposta = aposta;
	}

	public Segment obtenirInstruccio() throws ClassNotFoundException, IOException {
		s = (Segment) manager.getServer().obtenirTrama();
		if(s!=null) System.out.println(Calendar.getInstance().getTime().toString() + " soc un " + s.getClass());
		return s;
	}

	/**
	 * Metode que no retorna res i que s'encarrega de mostrar el gif.
	 */
	private void mostragif() {
		game.insereixGif();
	}//Tancament del metode
	
	public void setActive(boolean active) {
		this.active = active;
	}
	public boolean isActive(){
		return active;
	}

	public String getNumber() {
		return number;
	}

}//Tancament de la classe
